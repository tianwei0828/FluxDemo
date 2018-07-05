package com.tw.net.utils;


import com.orhanobut.logger.Logger;
import com.tw.net.config.Config;
import com.tw.utils.app.RxJavaUtil;
import com.tw.utils.base.ObjectUtil;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retrofit 工具类
 * Created by wei.tian
 * 2017/7/3
 */

public final class RetrofitUtil {
    private RetrofitUtil() {
        throw new IllegalStateException("No instance!");
    }

    private static CompositeDisposable sCompositeDisposable;

    public static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpUtil.buildClient())
                .baseUrl(Config.getBaseUrl())
                .build();
    }

    public static void addCall(Disposable call) {
        if (ObjectUtil.isNull(call) || call.isDisposed()) {
            Logger.d("addCall: illegal call");
            return;
        }
        synchronized (RetrofitUtil.class) {
            sCompositeDisposable = RxJavaUtil.getCompositeDisposable(sCompositeDisposable);
            if (sCompositeDisposable.isDisposed()) {
                Logger.d("addCall: already canceled");
                return;
            }
            Logger.d("addCall: do add");
            sCompositeDisposable.add(call);
        }
    }

    public static void cancelCall(Disposable call) {
        if (ObjectUtil.isNull(call) || call.isDisposed()) {
            Logger.d("cancelCall: illegal call");
            return;
        }
        synchronized (RetrofitUtil.class) {
            if (sCompositeDisposable == null || sCompositeDisposable.isDisposed()) {
                Logger.d("cancelCall: already canceled");
                return;
            }
            Logger.d("cancelCall: do cancel");
            sCompositeDisposable.remove(call);
        }
    }

    public static void cancelAll() {
        Logger.d("cancelAll");
        synchronized (RetrofitUtil.class) {
            if (sCompositeDisposable == null) {
                Logger.d("cancelAll: already canceled");
                return;
            }
            if (sCompositeDisposable.isDisposed()) {
                sCompositeDisposable = null;
                Logger.d("cancelAll: already canceled");
                return;
            }
            Logger.d("cancelAll: do cancelAll");
            sCompositeDisposable.clear();
            sCompositeDisposable = null;
        }
    }
}
