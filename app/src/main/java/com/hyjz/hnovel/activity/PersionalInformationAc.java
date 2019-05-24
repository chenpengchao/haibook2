package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.MainActivity;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.MyInfoBean;
import com.hyjz.hnovel.presenter.PersionInfoPresenter;
import com.hyjz.hnovel.utils.SharedPreferencesHelper;
import com.hyjz.hnovel.utils.ToastUtil;
import com.hyjz.hnovel.utils.Util;
import com.hyjz.hnovel.view.PersionInfoView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class PersionalInformationAc extends BaseActivity<PersionInfoPresenter> implements PersionInfoView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    //真实姓名
    @BindView(R.id.tv_myinfo_name)
    TextView tv_myinfo_name;
    //手机号码
    @BindView(R.id.tv_myinfo_phonenum)
    TextView tv_myinfo_phonenum;
    //微信号
    @BindView(R.id.tv_myinfo_wechat)
    TextView tv_myinfo_wechat;
    //性别
    @BindView(R.id.tv_myinfo_gender)
    TextView tv_myinfo_gender;
    //地区
    @BindView(R.id.tv_myinfo_area)
    TextView tv_myinfo_area;

    //支付宝提现账号
    @BindView(R.id.tv_alipay_withdraw)
    TextView tv_alipay_withdraw;
    //切换账户
    @BindView(R.id.tv_qiehuan)
    TextView tv_qiehuan;
    //退出账户
    @BindView(R.id.tv_quit)
    TextView tv_quit;
    @BindView(R.id.ll_gender)
    LinearLayout ll_gender;
    @BindView(R.id.ll_auto_buy)
    LinearLayout ll_auto_buy;
    @BindView(R.id.tv_myinfo_auto)
    TextView tv_myinfo_auto;
    //性别
    private String selectSex;
    private ArrayList<String> list = new ArrayList<>();
    //选择性别、
    private String[] a = {"男", "女"};
    Integer gender=0;
    //自动购买
    private String isAuto;
    private ArrayList<String> listAuto = new ArrayList<>();
    //选择性别、
    private String[] b = {"是", "否"};
    Integer auto=0;

    @Override
    public void initView() {
        title.setText("个人信息");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresenter.getInfo();
    }

    @Override
    protected PersionInfoPresenter createPresenter() {
        return new PersionInfoPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_persional_information;
    }

    @Override
    public void showLoading(String title) {
        showDialog();
    }

    @Override
    public void stopLoading() {
        dismissDialog();
    }

    @Override
    public void showErrorTip(String msg) {
        dismissDialog();
        ToastUtil.showShort(mContext,msg.trim().toString()+"");
    }

    @Override
    public void onSucess(MyInfoBean bean) {
        if (bean != null) {
//            显示姓名
            if (bean.getRealName() != null) {
                tv_myinfo_name.setText(bean.getRealName() + "");
            } else {
                tv_myinfo_name.setText( "");
            }
//          显示手机号码
            if (bean.getPhoneNum() != null) {
                tv_myinfo_phonenum.setText(bean.getPhoneNum() + "");
            } else {
                tv_myinfo_phonenum.setText( "");
            }
//            微信号
            if (bean.getWx_account() != null) {
                tv_myinfo_wechat.setText(bean.getWx_account() + "");
            } else {
                tv_myinfo_wechat.setText( "");
            }
//            性别
            if (bean.getGender() != null) {
                if (bean.getGender().equals("0")) {
                    tv_myinfo_gender.setText("男");
                } else {
                    tv_myinfo_gender.setText("女");
                }


            } else {
                tv_myinfo_gender.setText("");
            }
//            地区
            if (bean.getArea() != null) {
                tv_myinfo_area.setText(bean.getArea());
            }else {
                tv_myinfo_area.setText("");
            }
//            支付宝提现账户
            if (bean.getAlipayAccount() != null) {
                tv_alipay_withdraw.setText(bean.getAlipayAccount() + "");
            } else {
                tv_alipay_withdraw.setText("");
            }
            if (bean.getAutomaticDeduction() != null) {
                if (bean.getAutomaticDeduction() == 1) {
                    tv_myinfo_auto.setText("是");
                } else {
                    tv_myinfo_auto.setText("否");
                }
            }
        }

    }

    @Override
    public void onChangeSucess() {
        ToastUtil.showShort(mContext,"修改成功");
    }

    //点击事件
    @OnClick({R.id.tv_qiehuan,R.id.tv_quit,R.id.ll_gender,R.id.ll_auto_buy})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_qiehuan:
                Intent intent1 = new Intent(mContext, ShiftAc.class);
                startActivity(intent1);
                finish();
                break;
                //退出账户
            case R.id.tv_quit:
                SharedPreferencesHelper.remove("token");
                ToastUtil.showShort(mContext,"退出成功");
                Intent intent = new Intent(mContext, LoginAc.class);
                startActivity(intent);
                MainActivity.instance.finish();
                finish();
                break;
            case R.id.ll_gender:
                list.clear();
                for (int i = 0; i < Arrays.asList(a).size(); i++) {
                    list.add( Arrays.asList(a).get(i));
                }
                Util.alertBottomWheelOption(mContext, list, new Util.OnWheelViewClick() {
                    @Override
                    public void onClick(View view, int postion) {
                        selectSex = list.get(postion);
                        if (selectSex.equals("男")) {
                            gender = 0;
                        } else {
                            gender = 1;
                        }

                        tv_myinfo_gender.setText(selectSex);
                        mPresenter.changeInfo(MyApp.getInstance().getUserId(),gender,null);
                    }
                });
                break;
            case R.id.ll_auto_buy:
                listAuto.clear();
                for (int i = 0; i < Arrays.asList(b).size(); i++) {
                    listAuto.add( Arrays.asList(b).get(i));
                }
                Util.alertBottomWheelOption(mContext, listAuto, new Util.OnWheelViewClick() {
                    @Override
                    public void onClick(View view, int postion) {
                        isAuto = listAuto.get(postion);
                        if (isAuto.equals("否")) {
                            auto = 0;
                        } else {
                            auto = 1;
                        }

                        tv_myinfo_auto.setText(isAuto);
                        mPresenter.changeInfo(MyApp.getInstance().getUserId(),null,auto);
                    }
                });
                break;
        }
    }

    @Override
    public void failue() {

    }
}
