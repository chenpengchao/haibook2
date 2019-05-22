package com.hyjz.hnovel.ireader.presenter;

import com.hyjz.hnovel.ireader.model.remote.RemoteRepository;
import com.hyjz.hnovel.ireader.presenter.contract.SearchContract;
import com.hyjz.hnovel.base.RxPresenter;
import com.hyjz.hnovel.ireader.utils.LogUtils;
import com.hyjz.hnovel.ireader.utils.RxUtils;



/**
 * Created by newbiechen on 17-6-2.
 */

public class SearchPresenter extends RxPresenter<SearchContract.View>
        implements SearchContract.Presenter{

    @Override
    public void searchHotWord() {
        /*Disposable disp = RemoteRepository.getInstance()
                .getHotWords()
                .compose(RxUtils::toSimpleSingle)
                .subscribe(
                        bean -> {
                            mView.finishHotWords(bean);
                        },
                        e -> {
                            LogUtils.e(e);
                        }
                );
        addDisposable(disp);*/
    }

    @Override
    public void searchKeyWord(String query) {

    }

    @Override
    public void searchBook(String query) {

    }

   /* @Override
    public void searchKeyWord(String query) {
        Disposable disp = RemoteRepository.getInstance()
                .getKeyWords(query)
                .compose(RxUtils::toSimpleSingle)
                .subscribe(
                        bean -> {
                            mView.finishKeyWords(bean);
                        },
                        e -> {
                            LogUtils.e(e);
                        }
                );
        addDisposable(disp);
    }

    @Override
    public void searchBook(String query) {
        Disposable disp = RemoteRepository.getInstance()
                .getSearchBooks(query)
                .compose(RxUtils::toSimpleSingle)
                .subscribe(
                        bean -> {
                            mView.finishBooks(bean);
                        },
                        e -> {
                            LogUtils.e(e);
                            mView.errorBooks();
                        }
                );
        addDisposable(disp);
    }*/
}
