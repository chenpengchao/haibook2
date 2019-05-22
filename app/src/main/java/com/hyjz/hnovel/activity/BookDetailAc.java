package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.bean.BookDetailBean1;
import com.hyjz.hnovel.ireader.model.bean.BookDetailBean;
import com.hyjz.hnovel.ireader.model.bean.CollBookBean;
import com.hyjz.hnovel.presenter.BookDetailPresenter;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.view.BookDetailView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 小说详情页面
 */
public class BookDetailAc extends BaseActivity<BookDetailPresenter> implements BookDetailView {
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.back)
    ImageView back;
    //书籍封面
    @BindView(R.id.iv_book_cover)
    ImageView iv_book_cover;
    //小说标题
    @BindView(R.id.tv_book_title)
    TextView tv_book_title;
    //小说作者
    @BindView(R.id.tv_author)
    TextView tv_author;
    //小说字数
    @BindView(R.id.tv_book_num)
    TextView tv_book_num;
    //小说是否完结
    @BindView(R.id.tv_is_end)
    TextView tv_is_end;
    //书籍标签
    @BindView(R.id.tv_book_tag)
    TextView tv_book_tag;
    @BindView(R.id.tv_reader)
    TextView tv_reader;
    private CollBookBean mCollBookBean;
    private boolean isCollected = false;
    Long bookId;
    Long chapterId;
    @Override
    public void initView() {
        title.setText("详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bookId = getIntent().getLongExtra("bookId", 0l);
        mPresenter.getbookdetail(bookId);
//        mPresenter.getbookdetail1("53663ae356bdc93e49004474");
    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_detail;
    }

    @OnClick({R.id.tv_reader})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_reader:
                Intent intent = new Intent(mContext, ReadActivity.class)
                .putExtra(ReadActivity.EXTRA_IS_COLLECTED, isCollected)
                    .putExtra(ReadActivity.EXTRA_COLL_BOOK, mCollBookBean);
                startActivity(intent);

                break;
        }
    }
    @Override
    public void success(BookDetailBean1 bean) {

        mCollBookBean = detailtoCollBook(bean);
        GlideUtils.load(mContext, bean.getBookInfo().getBookCover(), iv_book_cover);
        tv_book_title.setText(bean.getBookInfo().getBookName()+"");
        tv_author.setText(bean.getBookInfo().getAuthorName() + "");
        tv_book_num.setText(bean.getBookInfo().getWordNum()+"字");
        if (bean.getBookInfo().getBookStatus() == 1) {
            tv_is_end.setText("");
        }
        chapterId = bean.getBookInfo().getLastChapterId();

//        for (int i = 0; i < bean.getBookInfo().getTags().size(); i++) {
//            if (i<)
//        }


    }

    @Override
    public void success1(BookDetailBean bookDetailBean) {
//        mCollBookBean = bookDetailBean.getCollBookBean();
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    /**
     * creater : iPhone 5 (GSM+CDMA)
     * longIntro : 一死今生了却凡尘！         (Collect)
     重生洪荒造化苍生！
     天道之下尽皆蝼蚁！
     唯有异数勘破万法！
     且看主角这个穿入洪荒世界的异数如何：
     造化福泽苍生
     道法纵横天地
     挣脱天道束缚
     一剑破空而去
     自此逍遥无束...
     书友群：209425550
     * cat : 洪荒封神
     * majorCate : 仙侠
     * minorCate : 洪荒封神
     * _le : false
     * allowMonthly : false
     * allowVoucher : true
     * allowBeanVoucher : false
     * hasCp : true              (Collect)
     * postCount : 121
     * latelyFollower : 1233      (Collect)
     * followerCount : 35
     * wordCount : 5947980
     * serializeWordCount : 4614
     * retentionRatio : 18.04     (Collect)
     * isSerial : true
     * chaptersCount : 1294         (Collect)
     * gender : ["male"]
     * donate : false
     * copyright : 阅文集团正版授权
     */
    public CollBookBean detailtoCollBook(BookDetailBean1 bean) {
        CollBookBean bookDetailBean = new CollBookBean();
        bookDetailBean.set_id(bean.getBookInfo().getBookId().toString());
        bookDetailBean.setAuthor(bean.getBookInfo().getAuthorName());
        bookDetailBean.setCover(bean.getBookInfo().getBookCover());
        bookDetailBean.setTitle(bean.getBookInfo().getBookName());
        bookDetailBean.setLastChapter(bean.getBookInfo().getLastChapterTitle());
        bookDetailBean.setUpdated(bean.getBookInfo().getUpdateTime());
        bookDetailBean.setLastChapterId(bean.getBookInfo().getLastChapterId());

        return bookDetailBean;
    }
}
