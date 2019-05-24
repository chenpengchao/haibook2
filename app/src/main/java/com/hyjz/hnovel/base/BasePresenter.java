package com.hyjz.hnovel.base;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.api.ApiRetrofit;
import com.hyjz.hnovel.api.ApiService;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.utils.ToastUtil;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V> {
    public Context mContext=MyApp.getContext();
    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();
    protected V mView;
    private CompositeSubscription mCompositeSubscription;
    public String mPrompt = "";
    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
        onUnsubscribe();
    }

    public<T> void addSubscription(Observable observable, Subscriber<T> subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
//        mCompositeSubscription.add(observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber));
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onStart() {
                        super.onStart();

                        subscriber.onStart();
                    }

                    @Override
                    public void onCompleted() {

                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                        if (e instanceof retrofit2.adapter.rxjava.HttpException) {
                            retrofit2.adapter.rxjava.HttpException ex=(retrofit2.adapter.rxjava.HttpException)e;
                            int code=ex.code();
                            switch (code){
                                case 504:
                                    ToastUtil.showShort(mContext,"您的网络连接可能有问题，请检查后再试");
                                    break;
                                case 408:
                                    ToastUtil.showShort(mContext,"您的网络连接可能有问题，请检查后再试");
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onNext(T o) {
                        if (o instanceof BaseBean){
                            BaseBean bean= (BaseBean)o;
                            if (bean.getMessage().equals("SUCCESS")){
                                subscriber.onNext(o);
                            }
                        }
                    }
                }));
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


}