package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.BookShelfAdapter;
import com.hyjz.hnovel.adapter.ReadCodeAdapter;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.bean.ReaderCodeBean;
import com.hyjz.hnovel.ireader.model.bean.CollBookBean;
import com.hyjz.hnovel.presenter.ReaderCodePresenter;
import com.hyjz.hnovel.view.ReaderCodeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 阅读记录页面
 */
public class ReaderCodeAc extends BaseActivity <ReaderCodePresenter> implements ReaderCodeView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.back)
    ImageView back;
    ReadCodeAdapter mAdapter;
    //书架图书列表
    private List<BookRecommend> list = new ArrayList<>();
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int mPage = 1;
    private static final int PAGE_SIZE = 10;
//    ImageView iv_header_book_shelf;
//    //头部
//    View header;
    private boolean isCollected = false;
    private CollBookBean mCollBookBean;
    @Override
    public void initView() {
        title.setText("阅读记录");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        initRecycler();
        loaddata();
        initRefresh();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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


        mPresenter.getReadCode(mPage);

    }

    /**
     * time    : 2019/3/14 11:24
     * desc    : 初始化列表
     * versions: 1.0
     */
    private void initRecycler() {
        mAdapter = new ReadCodeAdapter();

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
    protected ReaderCodePresenter createPresenter() {
        return new ReaderCodePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_reader_code;
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showErrorTip(String msg) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void onShowReadCode(ReaderCodeBean b) {
        mRefreshLayout.setLoadmoreFinished(b.getList().size() < PAGE_SIZE);
        mAdapter.addData(b.getList());

    }

    @Override
    public void onError() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }
}
