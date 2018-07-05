package com.tw.net;


import com.tw.net.internal.RealHttpRequest;
import com.tw.utils.base.ObjectUtil;

import io.reactivex.disposables.Disposable;

/**
 * Created by wei.tian
 * 2017/9/16
 */

public final class HttpRequest {
    private static RealHttpRequest sDelegate;

    private HttpRequest() {
        throw new IllegalStateException("No Instance!");
    }


    public static void init(Options options) {
        sDelegate = new RealHttpRequest().init(options);
    }

    private static RealHttpRequest checkAndGet() {
        return ObjectUtil.requireNonNull(sDelegate, "init() should be called first");
    }

    public static <T> T create(Class<T> service) {
        return checkAndGet().create(service);
    }

    public static void addCall(Disposable call) {
        checkAndGet().addCall(call);
    }

    public static void cancelCall(Disposable call) {
        checkAndGet().cancelCall(call);
    }

    public static void cancelAll() {
        checkAndGet().cancelAll();
    }
}
