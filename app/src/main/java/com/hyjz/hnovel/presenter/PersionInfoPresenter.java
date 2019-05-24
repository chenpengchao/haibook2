package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.MyInfoBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.PersionInfoView;

import rx.Subscriber;

public class PersionInfoPresenter  extends BasePresenter<PersionInfoView> {
    public PersionInfoPresenter(PersionInfoView view) {
        super(view);
    }
    public void getInfo() {
        mView.showLoading("");
        addSubscription(mApiService.getPersioninfo(MyApp.getInstance().getToken()).map((str) -> GsonUtils.fromJson(str, MyInfoBean.class))
                , new Subscriber<BaseBean<MyInfoBean>>(){
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                            mView.stopLoading();
                    }

                    @Override
                    public void onNext(BaseBean<MyInfoBean> myInfoBeanBaseBean) {
                        mView.onSucess(myInfoBeanBaseBean.getResult());
                    }

                }
        );
    }
    public void changeInfo(Long userId,Integer gender,Integer automaticDeduction) {
        mView.showLoading("");
        addSubscription(mApiService.changePersioninfo(MyApp.getInstance().getToken(),userId,gender,automaticDeduction).map((str) -> GsonUtils.fromJson(str, String.class))
                , new Subscriber<BaseBean<String>>(){
                    @Override
                    public void onCompleted() {
                    mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.stopLoading();
                    }

                    @Override
                    public void onNext(BaseBean<String> myInfoBeanBaseBean) {
                        mView.onChangeSucess();
                    }

                }
        );
    }
}
