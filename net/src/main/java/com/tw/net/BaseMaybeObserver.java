package com.tw.net;


import com.tw.net.internal.BaseObserver;
import com.tw.net.internal.RxErrorHandler;

import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wei.tian
 * 2017/9/18
 */

public abstract class BaseMaybeObserver<T> implements MaybeObserver<T>, BaseObserver {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        HttpRequest.addCall(d);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onError(RxErrorHandler.createRxException(e));
    }
}
