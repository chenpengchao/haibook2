package com.hyjz.hnovel.ireader.model.remote;

import com.hyjz.hnovel.ireader.model.bean.BookDetailBean;
import com.hyjz.hnovel.ireader.model.bean.packages.BillBookPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BillboardPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookChapterPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookCommentPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookHelpsPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookListDetailPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookListPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookReviewPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookSortPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookSubSortPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookTagPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.ChapterInfoPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.CommentDetailPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.CommentsPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.HelpsDetailPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.HotCommentPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.HotWordPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.KeyWordPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.RecommendBookListPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.RecommendBookPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.ReviewDetailPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.SearchBookPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.SortBookPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.TagSearchPackage;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by newbiechen on 17-4-20.
 */

public interface BookApi {


    /**
     * 推荐书籍
     * @param gender
     * @return
     */
    @GET("/book/recommend")
    Observable<RecommendBookPackage> getRecommendBookPackage(@Query("gender") String gender);


    /**
     * 获取书籍的章节总列表
     * @param bookId
     * @param view 默认参数为:chapters
     * @return
     */
    @GET("/mix-atoc/53663ae356bdc93e49004474")
    Observable<BookChapterPackage> getBookChapterPackage(@Path("bookId") String bookId, @Query("view") String view);

    /**
     * 章节的内容
     * 这里采用的是同步请求。
     * @param url
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/http%3A%2F%2Fbook.xbiquge.com%2FgetBooks.aspx%3Fmethod%3Dcontent%26bookId%3D857368%26chapterFile%3DU_857368_201806221650287145_7857_14.txt")
    Observable<ChapterInfoPackage> getChapterInfoPackage(@Path("url") String url);

    /*******************************Community *******************************************************/
    /**
     * 获取综合讨论区、原创区，女生区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=true
     *
     * @param block      ramble:综合讨论区
     *                   original：原创区
     *                   girl:女生区
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param type       all
     * @param start      0
     * @param limit      20
     * @param distillate true(精品)
     * @return
     */
    @GET("/post/by-block")
    Observable<BookCommentPackage> getBookCommentList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);


    /**
     * 获取书荒区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/help")
    Observable<BookHelpsPackage> getBookHelpList(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书评区帖子列表
     * 全部、全部类型、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、玄幻奇幻、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=xhqh&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   helpful(最有用的)
     *                   comment-count(最多评论)
     * @param type       all(全部类型)、xhqh(玄幻奇幻)、dsyn(都市异能)...
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/review")
    Observable<BookReviewPackage> getBookReviewList(@Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);


    /***********************************帖子详情*******************************************************/
    /**
     * 获取综合讨论区帖子详情 :/post/{detailId}
     * @param detailId ->_id
     * @return
     */
    @GET("/post/{detailId}")
    Observable<CommentDetailPackage> getCommentDetailPackage(@Path("detailId") String detailId);


    /**
     * 获取书评区帖子详情
     *
     * @param detailId->_id
     * @return
     * */
    @GET("/post/review/{detailId}")
    Observable<ReviewDetailPackage> getReviewDetailPacakge(@Path("detailId") String detailId);


    /**
     * 获取书荒区帖子详情
     *
     * @param detailId->_id
     * @return
     **/
    @GET("/post/help/{detailId}")
    Observable<HelpsDetailPackage> getHelpsDetailPackage(@Path("detailId") String detailId);


     /**
     * 获取神评论列表(综合讨论区、书评区、书荒区皆为同一接口)
     *
     * @param detailId -> _id
     * @return
     **/
    @GET("/post/{detailId}/comment/best")
    Observable<CommentsPackage> getBestCommentPackage(@Path("detailId") String detailId);

     /**
     * 获取综合讨论区帖子详情内的评论列表        :/post/{disscussionId}/comment
     * 获取书评区、书荒区帖子详情内的评论列表     :/post/review/{disscussionId}/comment
     * @param detailId->_id
     * @param start              0
     * @param limit              30
     * @return
     **/
     @GET("/post/{detailId}/comment")
     Observable<CommentsPackage> getCommentPackage(@Path("detailId") String detailId, @Query("start") String start, @Query("limit") String limit);

     @GET("/post/review/{detailId}/comment")
     Observable<CommentsPackage> getBookCommentPackage(@Path("detailId") String detailId, @Query("start") String start, @Query("limit") String limit);


    /************************************find****************************************************/

    /**
     * 获取所有排行榜
     *
     * @return
     */
    @GET("/ranking/gender")
    Observable<BillboardPackage> getBillboardPackage();

    /**
     * 获取单一排行榜
     * 周榜：rankingId-> _id
     * 月榜：rankingId-> monthRank
     * 总榜：rankingId-> totalRank
     *
     * @return
     */
    @GET("/ranking/{rankingId}")
    Observable<BillBookPackage> getBillBookPackage(@Path("rankingId") String rankingId);


    /*******************************分类***************************************/
    /**
     * 获取分类
     *
     * @return
     */
    @GET("/cats/lv2/statistics")
    Observable<BookSortPackage> getBookSortPackage();

    /**
     * 获取二级分类
     *
     * @return
     */
    @GET("/cats/lv2")
    Observable<BookSubSortPackage> getBookSubSortPackage();

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param limit  50
     * @return
     */
    @GET("/book/by-categories")
    Observable<SortBookPackage> getSortBookPackage(@Query("gender") String gender, @Query("type") String type, @Query("major") String major, @Query("minor") String minor, @Query("start") int start, @Query("limit") int limit);

    /********************************主题书单**************************************8*/

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * 如:http://api.zhuishushenqi.com/book-list?duration=last-seven-days&sort=collectorCount&start=0&limit=20&tag=%E9%83%BD%E5%B8%82&gender=male
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param limit  20
     * @return
     */
    @GET("/book-list")
    Observable<BookListPackage> getBookListPackage(@Query("duration") String duration, @Query("sort") String sort,
                                               @Query("start") String start, @Query("limit") String limit,
                                               @Query("tag") String tag, @Query("gender") String gender);

    /**
     * 获取主题书单标签列表
     *
     * @return
     */
    @GET("/book-list/tagType")
    Observable<BookTagPackage> getBookTagPackage();

    /**
     * 获取书单详情
     *
     * @return
     */
    @GET("/book-list/{bookListId}")
    Observable<BookListDetailPackage> getBookListDetailPackage(@Path("bookListId") String bookListId);


    /*************************书籍详情**********************************/

    /**
     * 书籍热门评论
     *
     * @param book
     * @return
     */
    @GET("/post/review/best-by-book")
    Observable<HotCommentPackage> getHotCommnentPackage(@Query("book") String book);

    /**
     * 书籍推荐书单
     * @param bookId
     * @param limit
     * @return
     */
    @GET("/book-list/{bookId}/recommend")
    Observable<RecommendBookListPackage> getRecommendBookListPackage(@Path("bookId") String bookId, @Query("limit") String limit);

    /**
     * 书籍详情
     * @param bookId
     * @return
     */
    @GET("/book/{bookId}")
    Observable<BookDetailBean> getBookDetail(@Path("bookId") String bookId);

    /**
     * 根据书籍的 Tag 进行检索
     * @param tags
     * @param start
     * @param limit
     * @return
     */
    @GET("/book/by-tags")
    Observable<TagSearchPackage> getTagSearchPackage(@Query("tags") String tags, @Query("start") String start, @Query("limit") String limit);


    /************************************搜索书籍******************************************************/
    @GET("/book/hot-word")
    Observable<HotWordPackage> getHotWordPackage();

    /**
     * 关键字自动补全
     *
     * @param query
     * @return
     */
    @GET("/book/auto-complete")
    Observable<KeyWordPackage> getKeyWordPacakge(@Query("query") String query);

    /**
     * 书籍查询
     *
     * @param query:作者名或者书名
     * @return
     */
    @GET("/book/fuzzy-search")
    Observable<SearchBookPackage> getSearchBookPackage(@Query("query") String query);
}
