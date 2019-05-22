package com.hyjz.hnovel.ireader.presenter;

import com.hyjz.hnovel.ireader.model.flag.BookSortListType;
import com.hyjz.hnovel.ireader.model.remote.RemoteRepository;
import com.hyjz.hnovel.ireader.presenter.contract.BookSortListContract;
import com.hyjz.hnovel.base.RxPresenter;
import com.hyjz.hnovel.ireader.utils.LogUtils;


/**
 * Created by newbiechen on 17-5-3.
 */

public class BookSortListPresenter extends RxPresenter<BookSortListContract.View>
        implements BookSortListContract.Presenter{
    @Override
    public void refreshSortBook(String gender, BookSortListType type, String major, String minor, int start, int limit) {

        if (minor.equals("全部")){
            minor = "";
        }

       /* Disposable refreshDispo = RemoteRepository.getInstance()
                .getSortBooks(gender,type.getNetName(),major,minor,start,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans)-> {
                            mView.finishRefresh(beans);
                            mView.complete();
                        }
                        ,
                        (e) ->{
                            mView.complete();
                            mView.showError();
                            LogUtils.e(e);
                        }
                );
        addDisposable(refreshDispo);*/
    }

    @Override
    public void loadSortBook(String gender, BookSortListType type, String major, String minor, int start, int limit) {
        /*Disposable loadDispo = RemoteRepository.getInstance()
                .getSortBooks(gender,type.getNetName(),major,minor,start,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans)-> {
                            mView.finishLoad(beans);
                        }
                        ,
                        (e) ->{
                            mView.showLoadError();
                            LogUtils.e(e);
                        }
                );
        addDisposable(loadDispo);*/
    }
}
