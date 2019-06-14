package com.hyjz.hnovel;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.hyjz.hnovel.activity.LoginAc;
import com.hyjz.hnovel.activity.RegesterAc;
import com.hyjz.hnovel.app.AppConfig;
import com.hyjz.hnovel.app.AppConstant;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.LoginBean;
import com.hyjz.hnovel.bean.MaiDianBean1;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.bean.TabEntity;
import com.hyjz.hnovel.fragment.BookCircleFm;
import com.hyjz.hnovel.fragment.FirstFm;
import com.hyjz.hnovel.fragment.HaiBookShelfFm;
import com.hyjz.hnovel.fragment.HiMoneyFm;
import com.hyjz.hnovel.fragment.MineFm;
import com.hyjz.hnovel.manager.ChangeModeController;
import com.hyjz.hnovel.presenter.LoginPresenter;
import com.hyjz.hnovel.presenter.MainAcPresenter;
import com.hyjz.hnovel.utils.LogUtils;
import com.hyjz.hnovel.utils.Md5Utils;
import com.hyjz.hnovel.utils.NetUtils;
import com.hyjz.hnovel.utils.NetWorkUtils;
import com.hyjz.hnovel.utils.SharedPreferencesHelper;
import com.hyjz.hnovel.utils.TimeUtils;
import com.hyjz.hnovel.view.LoginView;
import com.hyjz.hnovel.view.MainAcView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.http.Url;
import rx.functions.Action1;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles = {"嗨书架","首页", "嗨赚","嗨书圈","我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.bookshelf_normal,R.mipmap.home_normal,R.mipmap.himoney_normal,R.mipmap.circle_normal,R.mipmap.my_normal};
    private int[] mIconSelectIds = {
            R.mipmap.bookshelf_selected,R.mipmap.home_selected, R.mipmap.himoney_selected,R.mipmap.circle_selected,R.mipmap.my_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private HaiBookShelfFm haiBookShelfFm;
    private FirstFm firstFm;
    private HiMoneyFm hiMoneyFm;
    private BookCircleFm bookCircleFm;
    private MineFm mineFm;
    int pos= 0;
    private static int tabLayoutHeight;
    Bundle savedInstance = null;
    public static MainActivity instance;
    /**
     * 埋点相关
     */
    private String fromUrl = "/";
    private String toUrl = "";
    private String fromId = "";
    private String toId = "";
    //开始时间
    private Long startTime = 0l;
    //结束时间
    private Long endTime = 0l;
    private boolean isFirst = true;
    //停留时间
    private Long stayTime = 0l;



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void message(MessageEvent event) {
        if (event.getMessage().equals("login_success")) {
            SwitchTo(0);
//            tabLayout.setCurrentTab(0);
            EventBus.getDefault().post(new MessageEvent("refresh_fm_mine"));
        } else if (event.getMessage().equals("show_book_shelf")){
            SwitchTo(0);
//            tabLayout.setCurrentTab(0);
        } else if (event.getMessage().equals("show_hi_money_fm")) {
            SwitchTo(2);
//            tabLayout.setCurrentTab(2);
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    public void initView() {
//        //此处填上在http://fir.im/注册账号后获得的API_TOKEN以及APP的应用ID
//        UpdateKey.API_TOKEN = AppConfig.API_FIRE_TOKEN;
//        UpdateKey.APP_ID = AppConfig.APP_FIRE_ID;
//        //如果你想通过Dialog来进行下载，可以如下设置
////        UpdateKey.DialogOrNotification=UpdateKey.WITH_DIALOG;
//        UpdateFunGO.init(this);
        //初始化菜单
        startTime = TimeUtils.getCurrentSystemTime();
            initTab();
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //切换daynight模式要立即变色的页面
        ChangeModeController.getInstance().init(this,R.attr.class);
        super.onCreate(savedInstanceState);
        savedInstance = savedInstanceState;
        instance = this;
        //初始化frament
        if (MyApp.getInstance().getPhoneNum() != null && MyApp.getInstance().getPwd() != null) {
            SharedPreferencesHelper.remove("token");
            mPresenter.login(MyApp.getInstance().getPhoneNum(), MyApp.getInstance().getPwd());
        } else {
            if (MyApp.getInstance().getToken() != null) {
                SharedPreferencesHelper.remove("token");
            }

        }
//        setData();
//        initFragment(savedInstanceState);
        isFirst = true;
        SwitchTo(1);
//        tabLayout.setCurrentTab(1);
        tabLayout.measure(0,0);
        tabLayoutHeight=tabLayout.getMeasuredHeight();
        //监听菜单显示或隐藏
        mRxManager.on(AppConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {

            @Override
            public void call(Boolean hideOrShow) {
                startAnimation(hideOrShow);
            }
        });

    }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction1 用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction1) {
        if (haiBookShelfFm != null) {
            transaction1.hide(haiBookShelfFm);
        }
        if (firstFm != null) {
            transaction1.hide(firstFm);
        }
        if (hiMoneyFm != null) {
            transaction1.hide(hiMoneyFm);
        }
        if (bookCircleFm != null) {
            transaction1.hide(bookCircleFm);
        }
        if (mineFm != null) {
            transaction1.hide(mineFm);
        }
    }
    /**
     * 菜单显示隐藏动画
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide){
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if(!showOrHide){
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        }else{
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }
    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                isFirst = false;
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 1;
        if (savedInstanceState != null) {
            haiBookShelfFm = (HaiBookShelfFm) getSupportFragmentManager().findFragmentByTag("haiBookShelfFm");
            firstFm = (FirstFm) getSupportFragmentManager().findFragmentByTag("firstFm");
            hiMoneyFm = (HiMoneyFm) getSupportFragmentManager().findFragmentByTag("hiMoneyFm");
            bookCircleFm = (BookCircleFm) getSupportFragmentManager().findFragmentByTag("bookCircleFm");
            mineFm = (MineFm) getSupportFragmentManager().findFragmentByTag("mineFm");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {

            haiBookShelfFm = new HaiBookShelfFm();
            firstFm = new FirstFm();
            hiMoneyFm = new HiMoneyFm();

            mineFm = new MineFm();
            transaction.add(R.id.fl_body, bookCircleFm, "bookCircleFm");
            transaction.add(R.id.fl_body, haiBookShelfFm, "haiBookShelfFm");
            transaction.add(R.id.fl_body, firstFm, "firstFm");
            transaction.add(R.id.fl_body, hiMoneyFm, "hiMoneyFm");

            transaction.add(R.id.fl_body, mineFm, "mineFm");
//            transaction.addToBackStack(null);
            transaction.commit();
        }

        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (position) {
            //首页
            case 0:

                pos =0;

                if (haiBookShelfFm == null) {
                    haiBookShelfFm = new HaiBookShelfFm();
//                    jiaoliuFm = new BlackWeb();
                    transaction.add(R.id.fl_body, haiBookShelfFm);
                } else {
                    transaction.show(haiBookShelfFm);
                }
                SwitchTo(position);
                tabLayout.setCurrentTab(position);
                toUrl = "/bookShelf";
                toId = "bookShelf";
                stayTime =(System.currentTimeMillis() - startTime)/1000;
                startTime = System.currentTimeMillis();
                    mPresenter.setData(setData(stayTime,startTime,fromUrl,toUrl,fromId,toId));
                fromUrl = "/bookShelf";
                fromId = "bookShelf";

                break;
            //
            case 1:
                pos = 1;
                if (firstFm == null) {
                    firstFm = new FirstFm();
//                    jiaoliuFm = new BlackWeb();
                    transaction.add(R.id.fl_body, firstFm);
                } else {
                    transaction.show(firstFm);
                }
//                SwitchTo(position);
                tabLayout.setCurrentTab(position);
//
                toUrl = "/";
                toId = "boyChannel";
                stayTime =(System.currentTimeMillis() - startTime)/1000;
                startTime = System.currentTimeMillis();
                if (isFirst == true) {

                }else {
                    mPresenter.setData(setData(stayTime, startTime, fromUrl, toUrl, fromId, toId));
                }
                fromUrl = "/";
                fromId = "boyChannel";
                break;
            //
            case 2:
                pos = 2;
                if (hiMoneyFm == null) {
                    hiMoneyFm = new HiMoneyFm();
//                    jiaoliuFm = new BlackWeb();
                    transaction.add(R.id.fl_body, hiMoneyFm);
                } else {
                    transaction.show(hiMoneyFm);
                }
//                SwitchTo(position);
                tabLayout.setCurrentTab(position);
                toUrl = "/haiEarn";
                toId = "haiEarn";
                stayTime =(System.currentTimeMillis() - startTime)/1000;
                startTime = System.currentTimeMillis();
                    mPresenter.setData(setData(stayTime,startTime,fromUrl,toUrl,fromId,toId));
                fromUrl = "/haiEarn";
                fromId = "haiEarn";
                break;
            //
            case 3:
                pos = 3;
                if (bookCircleFm == null) {
                    bookCircleFm = new BookCircleFm();
//                    jiaoliuFm = new BlackWeb();
                    transaction.add(R.id.fl_body, bookCircleFm);
                } else {
                    transaction.show(bookCircleFm);
                }
//                SwitchTo(position);
                tabLayout.setCurrentTab(position);
                toUrl = "/haiMoments";
                toId = "haiMoments";
                stayTime =(System.currentTimeMillis() - startTime)/1000;
                startTime = System.currentTimeMillis();
                    mPresenter.setData(setData(stayTime,startTime,fromUrl,toUrl,fromId,toId));
                 fromUrl = "/haiMoments";
                fromId = "haiMoments";
                break;
            case 4:
                pos = 4;
                if (MyApp.getInstance().getToken() == null) {
                    startActivity(new Intent(this, LoginAc.class));
                } else {
                    if (mineFm == null) {
                        mineFm = new MineFm();
//                    jiaoliuFm = new BlackWeb();
                        transaction.add(R.id.fl_body, mineFm);
                    } else {
                        transaction.show(mineFm);
                    }
//                    SwitchTo(position);
                    tabLayout.setCurrentTab(position);
                    toUrl = "/personal";
                    toId = "personal";
                    stayTime =(System.currentTimeMillis() - startTime)/1000;
                    startTime = System.currentTimeMillis();
                        mPresenter.setData(setData(stayTime,startTime,fromUrl,toUrl,fromId,toId));
                    fromUrl = "/personal";
                    fromId = "personal";
                }

                break;
            default:
                break;
        }
        transaction.commit();
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

    @Override
    public void success(LoginBean b) {
//        MyApp.getInstance().setPwd(password);
//        MyApp.getInstance().setPhoneNum(phone);
        MyApp.getInstance().setNickName(b.getNickName());
        MyApp.getInstance().setUserId(b.getUserId());
        if (b.getHeadImg() != null) {
            MyApp.getInstance().setHeadImg(b.getHeadImg());
        }
        Log.d("token", b.getSessionId().toString().trim());
        MyApp.getInstance().setToken(b.getSessionId().toString().trim());
//        initFragment(savedInstanceState);
        SwitchTo(1);
    }

    @Override
    public void error() {
        startActivity(new Intent(this, LoginAc.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(false);
//            return true;
//        }
        if (pos == 1) {
            FirstFm.clickBack(keyCode, event);
            return true;
        }
        else if (pos == 2) {
            HiMoneyFm.clickBack(keyCode, event);
            return true;
        } else if (pos == 3) {
           BookCircleFm.clickBack(keyCode, event);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //埋点
    public String setData(Long staytime,Long starttime,String fromurl,String tourl,String fromid,String toid) {
// "client":2,"ipAddr":"223.68.198.123"}
        String s = null;
        Gson gson = new Gson();
        MaiDianBean1 bean1 = new MaiDianBean1();
        bean1.setStayTime(staytime);
        bean1.setTimestamp(starttime);
        bean1.setMemberId(MyApp.getInstance().getUserId().intValue());
        bean1.setFromUrl(fromurl);
        bean1.setToUrl(tourl);
        bean1.setFromId(Md5Utils.getMD5Str(fromid));
        bean1.setToId(Md5Utils.getMD5Str(toid));
        bean1.setDataType(1);
        bean1.setNetwork(NetWorkUtils.isWifi(mContext)?1:2);
        bean1.setBrowser("Chrome");
        bean1.setSoftwareType(2);
        bean1.setClient(2);
        bean1.setIpAddr(NetWorkUtils.getLocalIpAddress());

        String json = gson.toJson(bean1);
        String a = json.replaceAll("\\\"", "%22");
        String b = a.replaceAll("\\{", "%7B");
        String c = b.replaceAll("\\}", "%7D");
        String d = c.replaceAll("/", "%2F");


//        try {
//            s = URLEncoder.encode(json,"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
////        String s=  Uri.encode(json,":,0-9" );
//        Log.d("urlencoder", s);
        return d;
    }
}
