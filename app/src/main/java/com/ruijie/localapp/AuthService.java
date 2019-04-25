package com.ruijie.localapp;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//Android的四大组件，只有定义了，就必须去AndroidManifest.xml中注册一下！！！
public class AuthService extends Service {

    public static boolean AUTH_PASS = false;
    private final String TAG = "AuthService";


    //必须实现的方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "AuthService onBind方法被调用");
        return null;
    }

    //Service被创建时调用
    @Override
    public void onCreate() {
        Log.e(TAG, "AuthService onCreate方法被调用");
        //HashMap<String, String> result = DBUtils.getDiviceAuthInfo("111");
        //Log.e(TAG, result.toString());
        super.onCreate();
    }

    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "AuthService onStartCommand方法被调用");

        return super.onStartCommand(intent, flags, startId);
    }

    //Service被销毁时调用
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy方法被调用");

        super.onDestroy();
    }

}
