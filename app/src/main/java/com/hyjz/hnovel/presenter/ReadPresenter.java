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
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ReadPresenter  extends BasePresenter<ReadView> {
    public ReadPresenter(ReadView view) {
        super(view);
    }

    private Subscription mChapterSub;
    public void getChapterInfer(Integer chapterCoin,Long chapterId) {
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

    public void getChapterBeanList(Integer pageNum, Integer pageSize, Long bookId) {

        addSubscription(mApiService.getChapterList(MyApp.getInstance().getToken(), pageNum, pageSize,bookId).map((str) -> GsonUtils.fromJson(str, ChapterBeanList.class)),
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

    Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable)  observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public void OkHttpGet(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        //不配url方法会报错，肯定要有访问地址的嘛
        //.Builder是Request内部类 .url()返回Builder .build()返回Request对象
        Request request = new Request.Builder()
                //.addHeader("a", "b")//.addHeader添加键值对header信息
                //.get()//可加可不加
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
            //http状态码
            System.out.println(response.code());
            //response的头信息
            System.out.println(response.headers().toString());
            //请求响应时间，收到时间减去发送的时间，单位毫秒
            System.out.println(response.receivedResponseAtMillis()-response.sentRequestAtMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadCategory(String bookId) {

        Observable<BookChapterPackage> a= (Observable<BookChapterPackage>) ApiRetrofit1.getInstance().getApiService().getBookChapterPackage(bookId, "chapter")
                .map(bean -> {
                    if (bean.getMixToc() == null){
                        return new ArrayList<BookChapterBean>(1);
                    }
                    else {
                        return bean.getMixToc().getChapters();
                    }
                }).doOnNext(new Action1<List<BookChapterBean>>() {

            @Override
            public void call(List<BookChapterBean> bookChapterBeans) {
                //进行设定BookChapter所属的书的id。
                for (BookChapterBean bookChapter : bookChapterBeans) {
                    bookChapter.setId(MD5Utils.strToMd5By16(bookChapter.getLink()));
                    bookChapter.setBookId(bookId);
            }
        }}
        )
            .compose(schedulersTransformer())
                .subscribe(
                        beans -> {
                            mView.showCategory((List<BookChapterBean>)beans);
                        }
                        ,
                        e -> {
                            //TODO: Haven't grate conversation method.
                            LogUtils.e(e);
                        }
                );

    }


    public void loadChapter(String bookId, List<TxtChapter> bookChapters) {
        int size = bookChapters.size();

        //取消上次的任务，防止多次加载
        if (mChapterSub != null) {
            mChapterSub.unsubscribe();
        }
//        List<ChapterInfoBean> chapterInfos = new ArrayList<>(bookChapters.size());
        List<Observable<String>> chapterInfos = new ArrayList<>(bookChapters.size());
        ArrayDeque<String> titles = new ArrayDeque<>(bookChapters.size());

        // 将要下载章节，转换成网络请求。
        for (int i = 0; i < size; ++i) {
            TxtChapter bookChapter = bookChapters.get(i);
//            // 网络中获取数据
//            Observable<String> chapterInfoSingle =
                   addSubscription(mApiService.loadChapterInfo(MyApp.getInstance().getToken(), 0, bookChapter.getChapterId()).map((str)->GsonUtils.fromJson(str,ChapterInfoBean.class)),
                            new Subscriber<BaseBean<ChapterInfoBean>>() {
                                String title = titles.poll();
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
                                    //将获取到的数据进行存储
                                    title = titles.poll();
                                }
                            });


//            chapterInfos.add(chapterInfoSingle);

            titles.add(bookChapter.getTitle());
        }

//        Observable.concat(chapterInfos)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        new Subscriber<ChapterInfoBean>() {
//                            String title = titles.poll();
//
//
//
//                            @Override
//                            public void onNext(BaseBean<ChapterInfoBean> bean) {
//                                //存储数据
//                                BookRepository.getInstance().saveChapterInfo(
//                                        bookId, title, bean.getResult().getChapterContent()
//                                );
//                                mView.finishChapter();
//                                //将获取到的数据进行存储
//                                title = titles.poll();
//                            }
//
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable t) {
//                                //只有第一个加载失败才会调用errorChapter
//                                if (bookChapters.get(0).getTitle().equals(title)) {
//                                    mView.errorChapter();
//                                }
//                                LogUtils.e(t);
//                            }
//
//                            @Override
//                            public void onNext(ChapterInfoBean chapterInfoBean) {
//
//                            }
//
//
//                        }
//                );
    }


    public void detachView() {
        super.detachView();
        if (mChapterSub != null) {
            mChapterSub.unsubscribe();
        }
    }
}
