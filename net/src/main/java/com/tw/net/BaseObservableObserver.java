package com.tw.net;


import com.tw.net.internal.BaseObserver;
import com.tw.net.internal.RxErrorHandler;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wei.tian
 * 2017/9/18
 */

public abstract class BaseObservableObserver<T> implements Observer<T>, BaseObserver {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        HttpRequest.addCall(d);
    }

    @Override
    public void onError(Throwable e) {
        onError(RxErrorHandler.createRxException(e));
    }
}
