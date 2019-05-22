package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.api.ApiRetrofit1;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BookDetailBean1;
import com.hyjz.hnovel.ireader.utils.LogUtils;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.BookDetailView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookDetailPresenter extends BasePresenter<BookDetailView> {
    public BookDetailPresenter(BookDetailView view) {
        super(view);
    }
    public void getbookdetail(Long bookId) {
        addSubscription(mApiService.bookDetail(MyApp.getInstance().getToken(), bookId).map((str) -> GsonUtils.fromJson(str, BookDetailBean1.class)),
                new Subscriber<BaseBean<BookDetailBean1>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<BookDetailBean1> b) {
                        mView.success(b.getResult());
                    }
                });
    }

    public void getbookdetail1(String bookId) {
        ApiRetrofit1.getInstance().getApiService().getBookDetail(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<com.hyjz.hnovel.ireader.model.bean.BookDetailBean>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e);
                    }

                    @Override
                    public void onNext(com.hyjz.hnovel.ireader.model.bean.BookDetailBean bookDetailBean) {
                        mView.success1(bookDetailBean);
                    }
                });
    }

}
