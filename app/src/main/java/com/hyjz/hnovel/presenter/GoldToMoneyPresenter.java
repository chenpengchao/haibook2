package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.GoldtoBookCoinInfoBean;
import com.hyjz.hnovel.bean.GoldtoMoneyInfoBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.GoldtoMoneyView;

import rx.Subscriber;

public class GoldToMoneyPresenter extends BasePresenter<GoldtoMoneyView> {
    public GoldToMoneyPresenter(GoldtoMoneyView view) {
        super(view);
    }
    public void goldtobookcoininfo(Integer exchangeType) {
        mView.showLoading("加载中...");
        addSubscription(mApiService.goldtobookcoininfo(MyApp.getInstance().getToken(),exchangeType).map((str) -> GsonUtils.fromJson(str, GoldtoMoneyInfoBean.class)),
                new Subscriber<BaseBean<GoldtoMoneyInfoBean>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorTip(e.getMessage()+"");
                    }

                    @Override
                    public void onNext(BaseBean<GoldtoMoneyInfoBean> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            mView.onInfoSuccess(b.getResult());

                        }
                    }
                });
    }
    public void goldtomoney(Integer cash) {
        mView.showLoading("加载中...");
        addSubscription(mApiService.goldtomoney(MyApp.getInstance().getToken(),cash).map((str) -> GsonUtils.fromJson(str, String.class)),
                new Subscriber<BaseBean<String>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorTip(e.getMessage()+"");
                    }

                    @Override
                    public void onNext(BaseBean<String> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            mView.onGoldToMoneySuccess();
                        } else if (b.getMessage().equals("金币不足")) {
                            mView.onGoldToMoneyFailue();
                        }
                    }
                });
    }
}
