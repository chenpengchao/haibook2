package com.hyjz.hnovel.ireader.presenter;


import com.hyjz.hnovel.ireader.model.bean.BookChapterBean;
import com.hyjz.hnovel.ireader.model.bean.ChapterInfoBean;
import com.hyjz.hnovel.ireader.model.local.BookRepository;
import com.hyjz.hnovel.ireader.model.remote.RemoteRepository;
import com.hyjz.hnovel.ireader.presenter.contract.ReadContract;
import com.hyjz.hnovel.base.RxPresenter;
import com.hyjz.hnovel.ireader.utils.LogUtils;
import com.hyjz.hnovel.ireader.utils.MD5Utils;
import com.hyjz.hnovel.ireader.utils.RxUtils;
import com.hyjz.hnovel.ireader.widget.page.TxtChapter;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;


/**
 * Created by newbiechen on 17-5-16.
 */

public class ReadPresenter extends RxPresenter<ReadContract.View>
        implements ReadContract.Presenter {
    private static final String TAG = "ReadPresenter";
    private Subscription mChapterSub;
    @Override
    public void loadCategory(String bookId) {

    }

    @Override
    public void loadChapter(String bookId, List<TxtChapter> bookChapterList) {

    }

   /* private Subscription mChapterSub;

    @Override
    public void loadCategory(String bookId) {
        Disposable disposable = RemoteRepository.getInstance()
                .getBookChapters(bookId)
                .doOnSuccess(new Consumer<List<BookChapterBean>>() {
                    @Override
                    public void accept(List<BookChapterBean> bookChapterBeen) throws Exception {
                        //进行设定BookChapter所属的书的id。
                        for (BookChapterBean bookChapter : bookChapterBeen) {
                            bookChapter.setId(MD5Utils.strToMd5By16(bookChapter.getLink()));
                            bookChapter.setBookId(bookId);
                        }
                    }
                })
                .compose(RxUtils::toSimpleSingle)
                .subscribe(
                        beans -> {
                            mView.showCategory(beans);
                        }
                        ,
                        e -> {
                            //TODO: Haven't grate conversation method.
                            LogUtils.e(e);
                        }
                );
        addDisposable(disposable);
    }

    @Override
    public void loadChapter(String bookId, List<TxtChapter> bookChapters) {
        int size = bookChapters.size();

        //取消上次的任务，防止多次加载
        if (mChapterSub != null) {
            mChapterSub.cancel();
        }

        List<Single<ChapterInfoBean>> chapterInfos = new ArrayList<>(bookChapters.size());
        ArrayDeque<String> titles = new ArrayDeque<>(bookChapters.size());

        // 将要下载章节，转换成网络请求。
        for (int i = 0; i < size; ++i) {
            TxtChapter bookChapter = bookChapters.get(i);
            // 网络中获取数据
            Single<ChapterInfoBean> chapterInfoSingle = RemoteRepository.getInstance()
                    .getChapterInfo(bookChapter.getLink());

            chapterInfos.add(chapterInfoSingle);

            titles.add(bookChapter.getTitle());
        }

        Single.concat(chapterInfos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<ChapterInfoBean>() {
                            String title = titles.poll();

                            @Override
                            public void onSubscribe(Subscription s) {
                                s.request(Integer.MAX_VALUE);
                                mChapterSub = s;
                            }

                            @Override
                            public void onNext(ChapterInfoBean chapterInfoBean) {
                                //存储数据
                                BookRepository.getInstance().saveChapterInfo(
                                        bookId, title, chapterInfoBean.getBody()
                                );
                                mView.finishChapter();
                                //将获取到的数据进行存储
                                title = titles.poll();
                            }

                            @Override
                            public void onError(Throwable t) {
                                //只有第一个加载失败才会调用errorChapter
                                if (bookChapters.get(0).getTitle().equals(title)) {
                                    mView.errorChapter();
                                }
                                LogUtils.e(t);
                            }

                            @Override
                            public void onComplete() {
                            }
                        }
                );
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mChapterSub != null) {
            mChapterSub.cancel();
        }
    }*/

}
