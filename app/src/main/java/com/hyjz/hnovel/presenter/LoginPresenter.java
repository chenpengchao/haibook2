package com.hyjz.hnovel.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.hyjz.hnovel.api.ApiService;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.LoginBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.LoginView;

import rx.Subscriber;

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String phone, String password) {

        /**
         * {
         "success": true,
         "errorMsg": null,
         "errorCode": null,
         "access_token": "4a2f97b0-0b07-4df9-a993-493934ea9f90",
         "expire_time": 7200
         }
         */
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        @SuppressLint("MissingPermission")

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String deviceId = tm.getDeviceId();
        Log.e("DEVICE_ID ", deviceId + " ");
        mView.showLoading("");
        addSubscription(mApiService.login(phone, password).map((str) -> GsonUtils.fromJson(str, LoginBean.class)),
                new Subscriber<BaseBean<LoginBean>>() {

                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.stopLoading();
                    }

                    @Override
                    public void onNext(BaseBean<LoginBean> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            LoginBean bean = b.getResult();
                            mView.success(bean);
                        }
                    }
                });
    }
}
