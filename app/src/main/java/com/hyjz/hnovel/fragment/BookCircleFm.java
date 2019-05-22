package com.hyjz.hnovel.fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseFragment;
import com.hyjz.hnovel.base.BasePresenter;

import butterknife.BindView;


public class BookCircleFm extends BaseFragment {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_book_circle_fm;
    }

    @Override
    protected int getContentId() {
        return 0;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        back.setVisibility(View.INVISIBLE);
        title.setText("嗨书圈");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void processLogic() {

    }
}
