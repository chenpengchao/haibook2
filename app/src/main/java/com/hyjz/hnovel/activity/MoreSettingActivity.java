package com.hyjz.hnovel.activity;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.ireader.model.local.ReadSettingManager;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.ireader.presenter.ReadPresenter;
import com.hyjz.hnovel.presenter.LoginPresenter;

import butterknife.BindView;

/**
 * Created by newbiechen on 17-6-6.
 * 阅读界面的更多设置
 */

public class MoreSettingActivity extends BaseActivity{
    @BindView(R.id.more_setting_rl_volume)
    RelativeLayout mRlVolume;
    @BindView(R.id.more_setting_sc_volume)
    SwitchCompat mScVolume;
    @BindView(R.id.more_setting_rl_full_screen)
    RelativeLayout mRlFullScreen;
    @BindView(R.id.more_setting_sc_full_screen)
    SwitchCompat mScFullScreen;
    @BindView(R.id.more_setting_rl_convert_type)
    RelativeLayout mRlConvertType;
    @BindView(R.id.more_setting_sc_convert_type)
    Spinner mScConvertType;
    private ReadSettingManager mSettingManager;
    private boolean isVolumeTurnPage;
    private boolean isFullScreen;
    private int convertType;
    @Override
    protected int getContentId() {
        return R.layout.activity_more_setting;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {
        super.setUpToolbar(toolbar);
        getSupportActionBar().setTitle("阅读设置");
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initSwitchStatus();
    }

    private void initSwitchStatus(){
        mScVolume.setChecked(isVolumeTurnPage);
        mScFullScreen.setChecked(isFullScreen);
    }

    @Override
    protected void initClick() {
        super.initClick();
        mRlVolume.setOnClickListener(
                (v) -> {
                    if (isVolumeTurnPage){
                        isVolumeTurnPage = false;
                    }
                    else {
                        isVolumeTurnPage = true;
                    }
                    mScVolume.setChecked(isVolumeTurnPage);
                    mSettingManager.setVolumeTurnPage(isVolumeTurnPage);
                }
        );

        mRlFullScreen.setOnClickListener(
                (v) -> {
                    if (isFullScreen){
                        isFullScreen = false;
                    }
                    else {
                        isFullScreen = true;
                    }
                    mScFullScreen.setChecked(isFullScreen);
                    mSettingManager.setFullScreen(isFullScreen);
                }
        );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingManager = ReadSettingManager.getInstance();
        isVolumeTurnPage = mSettingManager.isVolumeTurnPage();
        isFullScreen = mSettingManager.isFullScreen();
        convertType = mSettingManager.getConvertType();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mScConvertType.setAdapter(adapter);

        // initSwitchStatus() be called earlier than onCreate(), so setSelection() won't work
        mScConvertType.setSelection(convertType);

        mScConvertType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSettingManager.setConvertType(position);
                convertType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_more_setting;
    }
}
