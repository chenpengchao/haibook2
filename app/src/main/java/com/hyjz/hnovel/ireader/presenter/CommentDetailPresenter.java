package com.hyjz.hnovel.ireader.presenter;

import com.hyjz.hnovel.ireader.model.bean.CommentBean;
import com.hyjz.hnovel.ireader.model.bean.CommentDetailBean;
import com.hyjz.hnovel.ireader.model.remote.RemoteRepository;
import com.hyjz.hnovel.ireader.presenter.contract.CommentDetailContract;
import com.hyjz.hnovel.base.RxPresenter;
import com.hyjz.hnovel.ireader.utils.LogUtils;
import com.hyjz.hnovel.ireader.utils.RxUtils;

import java.util.List;

/**
 * Created by newbiechen on 17-4-29.
 */

public class CommentDetailPresenter extends RxPresenter<CommentDetailContract.View>
        implements CommentDetailContract.Presenter {

    @Override
    public void refreshCommentDetail(String detailId, int start, int limit) {
        /*Single<CommentDetailBean> detailSingle = RemoteRepository
                .getInstance().getCommentDetail(detailId);

        Single<List<CommentBean>> bestCommentsSingle = RemoteRepository
                .getInstance().getBestComments(detailId);

        Single<List<CommentBean>> commentsSingle = RemoteRepository
                .getInstance().getDetailComments(detailId, start, limit);

        Disposable detailDispo = RxUtils.toCommentDetail(detailSingle, bestCommentsSingle, commentsSingle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (bean) -> {
                            mView.finishRefresh(bean.getDetail(),
                                    bean.getBestComments(), bean.getComments());
                            mView.complete();
                        },
                        (e) -> {
                            mView.showError();
                            LogUtils.e(e);
                        }
                );
        addDisposable(detailDispo);*/
    }

    @Override
    public void loadComment(String detailId, int start, int limit) {
       /* Disposable loadDispo = RemoteRepository.getInstance()
                .getDetailComments(detailId, start, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (bean) -> {
                            mView.finishLoad(bean);
                        },
                        (e) -> {
                            mView.showLoadError();
                            LogUtils.e(e);
                        }
                );
        addDisposable(loadDispo);*/
    }
}
