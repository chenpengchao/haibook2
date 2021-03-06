package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.bean.ReaderCodeBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.ReaderCodeView;

import rx.Subscriber;

public class ReaderCodePresenter  extends BasePresenter<ReaderCodeView> {
    public ReaderCodePresenter(ReaderCodeView view) {
        super(view);
    }
    public void getReadCode(Integer mPage) {
        addSubscription(mApiService.getReadCode(MyApp.getInstance().getToken(),mPage,10).map((str)->GsonUtils.fromJson(str,ReaderCodeBean.class)),
                new Subscriber<BaseBean<ReaderCodeBean>>() {
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
                    public void onNext(BaseBean<ReaderCodeBean> response) {
                        mView.onShowReadCode(response.getResult());
                    }

                });
    }
}
