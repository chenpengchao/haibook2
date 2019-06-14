package com.hyjz.hnovel.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chaychan.uikit.powerfulrecyclerview.PowerfulRecyclerView;
import com.chaychan.uikit.refreshlayout.BGANormalRefreshViewHolder;
import com.chaychan.uikit.refreshlayout.BGARefreshLayout;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.activity.BookDetailAc;
import com.hyjz.hnovel.activity.ReadActivity;
import com.hyjz.hnovel.activity.ShowWebNewAc;
import com.hyjz.hnovel.adapter.BookShelfAdapter;
import com.hyjz.hnovel.adapter.BookTicketConsumCodeAdapter;
import com.hyjz.hnovel.base.BaseFragment;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.ireader.model.bean.CollBookBean;
import com.hyjz.hnovel.presenter.BookShelfPresenter;
import com.hyjz.hnovel.utils.UIUtils;
import com.hyjz.hnovel.view.BookShelfView;
import com.hyjz.hnovel.weight.LoadingTip;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaiBookShelfFm extends BaseFragment<BookShelfPresenter> implements BookShelfView{
//    //加载页面内嵌提示
//    @BindView(R.id.loadedTip)
//    LoadingTip loadedTip;
//    @BindView(R.id.rv_news)
//    PowerfulRecyclerView mRvNews;
    BookShelfAdapter mAdapter;
    //书架图书列表
    private List<BookRecommend> list = new ArrayList<>();
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int mPage = 1;
    private static final int PAGE_SIZE = 15;
    ImageView iv_header_book_shelf;
    //头部
    View header;
    private boolean isCollected = false;
    private CollBookBean mCollBookBean;
    @Override
    protected BookShelfPresenter createPresenter() {
        return new BookShelfPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_hai_book_shelf_fm;
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();

//        mPresenter.getRecommed("male");

        mStateView.showContent();//显示内容
    }

    @Override
    protected void processLogic() {

    }

    @Override
    public void onShowRecommendData(BookRecommend b) {

        mRefreshLayout.setLoadmoreFinished(b.getList().size() < PAGE_SIZE);
        mAdapter.addData(b.getList());


    }

    @Override
    public void onError() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    protected int getContentId() {
        return 0;
    }

    @Override
    public void initView(View rootView) {
        header = View.inflate(mContext, R.layout.header_book_shelf, null);
        iv_header_book_shelf = header.findViewById(R.id.iv_header_book_shelf);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        initRecycler();
        loaddata();
        initRefresh();
        mAdapter.addHeaderView(header);
        iv_header_book_shelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentgetBookCoin = new Intent(mContext,ShowWebNewAc.class);

                intentgetBookCoin.putExtra("url", "http://www.haishuwu.com/bookTokenTask");
                intentgetBookCoin.putExtra("title", "每日任务");
                startActivity(intentgetBookCoin);
//                Intent intent = new Intent(mContext, ReadActivity.class)
//                        .putExtra(ReadActivity.EXTRA_IS_COLLECTED, isCollected)
//                        .putExtra(ReadActivity.EXTRA_COLL_BOOK, mCollBookBean);;
//                startActivity(intent);
            }
        });
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });
    }

    /**
     * time    : 2019/3/14 11:24
     * desc    : 初始化上拉刷新, 下拉加载更多
     * versions: 1.0
     * 255,69,0
     */
    private void initRefresh() {

        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                loaddata();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh();
            }
        });

    }

    /**
     * time    : 2019/3/28 11:22
     * desc    : 刷新列表
     * versions: 1.0
     */
    private void refresh() {
        mPage = 1;
        mAdapter.getData().clear();
        loaddata();
    }

    public void loaddata() {


        mPresenter.getRecommed(mPage);

    }

    /**
     * time    : 2019/3/14 11:24
     * desc    : 初始化列表
     * versions: 1.0
     */
    private void initRecycler() {
        mAdapter = new BookShelfAdapter();

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Intent intent = new Intent(mContext, ShowWebNewAc.class);
                intent.putExtra("title", "详情");
                intent.putExtra("url","http://www.haishuwu.com/detail?id="+ mAdapter.getData().get(position).getBookId());
                startActivity(intent);
//                Toast.makeText(mContext, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void initListener() {

    }


    @Override
    public void showLoading(String title) {
//        loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    @Override
    public void stopLoading() {
//        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showErrorTip(String msg) {
//        irc.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }
}
