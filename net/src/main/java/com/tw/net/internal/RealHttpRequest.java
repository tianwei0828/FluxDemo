package com.tw.net.internal;


import com.orhanobut.logger.Logger;
import com.tw.net.Options;
import com.tw.net.config.Config;
import com.tw.net.utils.RetrofitUtil;
import com.tw.utils.base.ObjectUtil;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

/**
 * Created by wei.tian
 * 2017/9/16
 */

public class RealHttpRequest {
    public RealHttpRequest init(Options options) {
        Logger.i("init");
        ObjectUtil.checkNonNull(options, "options == null");
        Config.setContext(options.getContext());
        Config.setsRetryOnConnectionFailure(options.isRetryOnConnectionFailure());
        Config.setConnectTimeout(options.getConnectTimeout());
        Config.setReadTimeout(options.getReadTimeout());
        Config.setWriteTimeout(options.getWriteTimeout());
        Config.setHosts(options.getHosts());
        Config.setCertificates(options.getCertificates());
        Config.setBaseUrl(options.getBaseUrl());
        Config.debug(options.isDebug());
        Config.setHttpHeaders(options.getHttpHeaders());
        Config.setParams(options.getParams());
        Config.setInterceptors(options.getInterceptors());
        Config.setNetworkInterceptors(options.getNetworkInterceptors());
        Retrofit retrofit = options.getRetrofit();
        if (ObjectUtil.isNull(retrofit)) {
            Config.setRetrofit(RetrofitUtil.buildRetrofit());
        } else {
            Config.setRetrofit(retrofit);
        }
        return this;
    }

    public <T> T create(Class<T> service) {
        ObjectUtil.checkNonNull(service, "service == null");
        return Config.getRetrofit().create(service);
    }

    public void addCall(Disposable call) {
        RetrofitUtil.addCall(call);
    }

    public void cancelCall(Disposable call) {
        RetrofitUtil.cancelCall(call);
    }

    public void cancelAll() {
        RetrofitUtil.cancelAll();
    }
}