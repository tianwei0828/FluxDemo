package com.tw.fluxdemo;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tw.fluxlib.BaseApp;
import com.tw.net.HttpRequest;
import com.tw.net.Options;

/**
 * 尽量不要在Application的onCreate方法中作太多耗时操作，会影响app的启动时间
 * 可以将某些耗时的初始化操作放在SplashActivity中完成
 * Created by wei.tian
 * 2018/7/2
 */
public class FluxDemoApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        initNet();
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .tag(getClass().getSimpleName())
                    .build();
            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        }
    }

    private void initNet() {
        Options options = new Options.Builder()
                .baseUrl(BuildConfig.BASE_SERVER_URL)
                .debug(BuildConfig.DEBUG)
                .build();
        HttpRequest.init(options);
    }
}
