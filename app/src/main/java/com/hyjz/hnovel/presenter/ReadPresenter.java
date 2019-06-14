package com.hyjz.hnovel.presenter;


import com.hyjz.hnovel.api.ApiRetrofit;
import com.hyjz.hnovel.api.ApiRetrofit1;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.ChapterBeanList;
import com.hyjz.hnovel.bean.ReadBean;
import com.hyjz.hnovel.ireader.model.bean.BookChapterBean;
import com.hyjz.hnovel.ireader.model.bean.ChapterInfoBean;
import com.hyjz.hnovel.ireader.model.bean.packages.BookChapterPackage;
import com.hyjz.hnovel.ireader.model.local.BookRepository;
import com.hyjz.hnovel.ireader.utils.LogUtils;
import com.hyjz.hnovel.ireader.utils.MD5Utils;
import com.hyjz.hnovel.ireader.utils.RxUtils;
import com.hyjz.hnovel.ireader.widget.page.TxtChapter;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.ReadView;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ReadPresenter extends BasePresenter<ReadView> {
    public ReadPresenter(ReadView view) {
        super(view);
    }

    private Subscription mChapterSub;

    public void getChapterInfer(Integer chapterCoin, Long chapterId) {
        addSubscription(mApiService.bookReader(MyApp.getInstance().getToken(), chapterCoin, chapterId).map((str) -> GsonUtils.fromJson(str, ReadBean.class)),
                new Subscriber<BaseBean<ReadBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<ReadBean> bean) {
                        mView.showChapterInfo(bean.getResult());
                    }
                });

    }

    /**
     * 获得目录列表
     * @param pageNum
     * @param pageSize
     * @param bookId
     */
    public void getChapterBeanList(Integer pageNum, Integer pageSize, Long bookId) {

        addSubscription(mApiService.getChapterList(MyApp.getInstance().getToken(), pageNum, pageSize, bookId).map((str) -> GsonUtils.fromJson(str, ChapterBeanList.class)),
                new Subscriber<BaseBean<ChapterBeanList>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<ChapterBeanList> bean) {
                        for (BookChapterBean bookChapter : bean.getResult().getList()) {
                            bookChapter.setId(MD5Utils.strToMd5By16(bookChapter.getChapterId().toString()));
                            bookChapter.setBookId(bookId.toString());
                        }
                        mView.showCategory(bean.getResult().getList());
                    }
                });
    }

    /**
     * 获得章节内容
     * @param bookId
     * @param bookChapters
     */
    public void loadChapter(String bookId, List<TxtChapter> bookChapters) {
        int size = bookChapters.size();

        //取消上次的任务，防止多次加载
        if (mChapterSub != null) {
            mChapterSub.unsubscribe();
        }
        // 将要下载章节，转换成网络请求。
        for (int i = 0; i < size; ++i) {
            TxtChapter bookChapter = bookChapters.get(i);

            addSubscription(mApiService.loadChapterInfo(MyApp.getInstance().getToken(), bookChapter.getChapterCoin(), bookChapter.getChapterId()).map((str) -> GsonUtils.fromJson(str, ChapterInfoBean.class)),
                    new Subscriber<BaseBean<ChapterInfoBean>>() {
                        String title = bookChapter.getTitle();

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(BaseBean<ChapterInfoBean> bean) {
                            //存储数据
                            BookRepository.getInstance().saveChapterInfo(
                                    bookId, title, bean.getResult().getChapterContent()
                            );
                            mView.finishChapter();
                        }
                    });
        }

    }

    /**
     * 添加收藏接口
     */
    public void addBookShelf(Long bookId) {

        addSubscription(mApiService.addBookShelf(MyApp.getInstance().getToken(),bookId).map((str) -> GsonUtils.fromJson(str, String.class)),
                new Subscriber<BaseBean<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<String> bean) {
                        if (bean.getMessage().equals("SUCCESS")) {

                            mView.addBookShelf();
                        }
                    }
                });
    }


    public void detachView() {
        super.detachView();
        if (mChapterSub != null) {
            mChapterSub.unsubscribe();
        }
    }
}
