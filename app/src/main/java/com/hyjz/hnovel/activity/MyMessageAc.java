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
import com.hyjz.hnovel.adapter.MyMessageAdapter;
import com.hyjz.hnovel.adapter.ReadCodeAdapter;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.bean.MyMessageBean;
import com.hyjz.hnovel.ireader.model.bean.CollBookBean;
import com.hyjz.hnovel.presenter.MyMesssagePresenter;
import com.hyjz.hnovel.view.MyMessageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的消息页面
 */

public class MyMessageAc extends BaseActivity<MyMesssagePresenter> implements MyMessageView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.back)
    ImageView back;
    MyMessageAdapter mAdapter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int mPage = 1;
    private static final int PAGE_SIZE = 15;
    @Override
    public void initView() {
        title.setText("我的消息");
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


        mPresenter.getMessageList(mPage);

    }

    /**
     * time    : 2019/3/14 11:24
     * desc    : 初始化列表
     * versions: 1.0
     */
    private void initRecycler() {
        mAdapter = new MyMessageAdapter();

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected MyMesssagePresenter createPresenter() {
        return new MyMesssagePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_message;
    }
    @OnClick({R.id.back})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
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
    public void onMessageSuccess(MyMessageBean b) {
        mRefreshLayout.setLoadmoreFinished(b.getList().size() < PAGE_SIZE);
        mAdapter.addData(b.getList());

    }
}
