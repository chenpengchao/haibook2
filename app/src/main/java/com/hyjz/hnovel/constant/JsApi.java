package com.hyjz.hnovel.constant;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.hyjz.hnovel.activity.ReadActivity;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BookDetailBean1;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.fragment.FirstFm;
import com.hyjz.hnovel.ireader.model.bean.BookDetailBean;
import com.hyjz.hnovel.ireader.model.bean.CollBookBean;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;

import wendu.dsbridge.CompletionHandler;

/**
 * Created by du on 16/12/31.
 */

public class JsApi {
    Gson gson = new Gson();

    public interface CallSuccessListener {
        void showInfo(BookDetailBean1.BookInfo b);

        void unToken();
    }

    CallSuccessListener listener;

    public void setListener(CallSuccessListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public String hideBar(Object msg)  {
//        return msg + "［syn call］";
        return "1";
    }
    @JavascriptInterface
    public void openBook(Object msg ) {
//        EventBus.getDefault().post(new MessageEvent());
        String json = msg.toString();
        BookDetailBean1.BookInfo bean = gson.fromJson(json, BookDetailBean1.BookInfo.class);

        listener.showInfo(bean);

    }
    @JavascriptInterface
    public void unToken(String msg ) {
        if (msg.equals("0")){
            listener.unToken();
        }



    }

    @JavascriptInterface
    public void testAsyn(Object msg, CompletionHandler<String> handler){
        handler.complete(msg+" [ asyn call]");
    }

    @JavascriptInterface
    public String testNoArgSyn(Object arg) throws JSONException {
        return  "testNoArgSyn called [ syn call]";
    }

    @JavascriptInterface
    public void testNoArgAsyn(Object arg, CompletionHandler<String> handler) {
        handler.complete( "testNoArgAsyn   called [ asyn call]");
    }


    //@JavascriptInterface
    //without @JavascriptInterface annotation can't be called
    public String testNever(Object arg) throws JSONException {
        JSONObject jsonObject= (JSONObject) arg;
        return jsonObject.getString("msg") + "[ never call]";
    }

    @JavascriptInterface
    public void callProgress(Object args, final CompletionHandler<Integer> handler) {

        new CountDownTimer(11000, 1000) {
            int i=10;
            @Override
            public void onTick(long millisUntilFinished) {
                //setProgressData can be called many times util complete be called.
                handler.setProgressData((i--));

            }
            @Override
            public void onFinish() {
                //complete the js invocation with data; handler will be invalid when complete is called
                handler.complete(0);

            }
        }.start();
    }
}