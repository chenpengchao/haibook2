package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的消息页面
 */

public class MyMessageAc extends BaseActivity {
    //标题
    @BindView(R.id.title)
    TextView title;
    //返回键
    @BindView(R.id.back)
    ImageView back;
    @Override
    public void initView() {
        title.setText("我的消息");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
}
