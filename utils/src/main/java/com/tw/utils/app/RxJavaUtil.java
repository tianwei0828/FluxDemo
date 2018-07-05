package com.tw.utils.app;


import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava 工具类
 * Created by wei.tian
 * 2017/7/4
 */

public final class RxJavaUtil {
    private RxJavaUtil() {
        throw new IllegalStateException("No instance!");
    }

    public static CompositeDisposable getCompositeDisposable(CompositeDisposable compositeDisposable) {
        if (compositeDisposable == null) {
            return new CompositeDisposable();
        }
        return compositeDisposable;
    }

    public static final <T> SingleTransformer<T, T> applySingleMainSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static final <T> FlowableTransformer<T, T> applyFlowableMainSchedulers() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static final <T> MaybeTransformer<T, T> applyMaybeMainSchedulers() {
        return maybe -> maybe.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static final CompletableTransformer applyCompletableMainSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static final <T> ObservableTransformer<T, T> applyObservableMainSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static final <T> SingleTransformer<T, T> applySingleIoSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    public static final <T> ObservableTransformer<T, T> applyObservableIoSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}