package com.tw.net.internal;


import com.tw.utils.base.ObjectUtil;

import okhttp3.Interceptor;

/**
 * 基类拦截器
 * Created by wei.tian on 2017/5/5.
 */

public abstract class BaseInterceptor<T> implements Interceptor {
    protected T t;

    protected BaseInterceptor(T t) {
        ObjectUtil.checkNonNull(t, "t == null");
        this.t = t;
    }

    protected T getParams() {
        return t;
    }
}
