package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.MyMessageBean;
import com.hyjz.hnovel.bean.ReaderCodeBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.MyMessageView;

import rx.Subscriber;

public class MyMesssagePresenter extends BasePresenter<MyMessageView> {
    public MyMesssagePresenter(MyMessageView view) {
        super(view);
    }
    public void getMessageList(Integer mPage) {
        addSubscription(mApiService.getMessage(MyApp.getInstance().getToken(),mPage,15).map((str)->GsonUtils.fromJson(str,MyMessageBean.class)),
                new Subscriber<BaseBean<MyMessageBean>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
//                e.printStackTrace();
//                KLog.e(e.getLocalizedMessage());
                        mView.stopLoading();
                    }

                    @Override
                    public void onNext(BaseBean<MyMessageBean> response) {
                        mView.onMessageSuccess(response.getResult());
                    }

                });
    }
}
