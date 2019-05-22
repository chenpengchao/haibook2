package com.hyjz.hnovel.base;



/**
 * Created by newbiechen on 17-4-26.
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    @Override
    public void attachView(T view) {

    }

    @Override
    public void detachView() {

    }

  /*  protected T mView;
    protected CompositeDisposable mDisposable;

    protected void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    protected void addDisposable(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }*/
}
