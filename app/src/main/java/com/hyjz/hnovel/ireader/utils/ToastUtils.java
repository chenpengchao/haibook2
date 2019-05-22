package com.hyjz.hnovel.ireader.utils;

import android.widget.Toast;

import com.hyjz.hnovel.app.MyApp;

/**
 * Created by newbiechen on 17-5-11.
 */

public class ToastUtils {

    public static void show(String msg){
        Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
