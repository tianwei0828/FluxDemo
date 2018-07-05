package com.tw.fluxlib;

import android.app.Application;
import android.content.Context;

/**
 * Created by wei.tian
 * 2018/7/2
 */
public class BaseApp extends Application {
    private static Context sContext;

    public static Context appContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
