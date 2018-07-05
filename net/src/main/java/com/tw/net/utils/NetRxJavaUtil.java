package com.tw.net.utils;


import com.orhanobut.logger.Logger;
import com.tw.net.Feed;
import com.tw.net.ServerException;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

/**
 * NetRxJavaUtil 工具类
 * Created by wei.tian
 * 2017/7/4
 */

public final class NetRxJavaUtil {
    private NetRxJavaUtil() {
        throw new IllegalStateException("No instance!");
    }

    private static final int CODE_SUCCEED = 200;

    public static final <T> FlowableTransformer<Feed<T>, T> applyFlowableFeedTransformer() {
        return upstream -> upstream.map(result -> {
            int status = result.getStatus();
            if (status == CODE_SUCCEED) {
                return result.getData();
            }
            throw new ServerException(status, result.getMessage());
        });
    }

    public static final <T> ObservableTransformer<Feed<T>, T> applyObservableFeedTransformer() {
        return upstream -> upstream.map(result -> {
            int status = result.getStatus();
            if (status == CODE_SUCCEED) {
                return result.getData();
            }
            throw new ServerException(status, result.getMessage());
        });
    }


    public static final <T> SingleTransformer<Feed<T>, T> applySingleFeedTransformer() {
        return upstream -> upstream.map(result -> {
            Logger.e("result: "+result);
            int status = result.getStatus();
            if (status == CODE_SUCCEED) {
                return result.getData();
            }
            throw new ServerException(status, result.getMessage());
        });
    }


    public static final <T> MaybeTransformer<Feed<T>, T> applyMaybeFeedTransformer() {
        return upstream -> upstream.map(result -> {
            int status = result.getStatus();
            if (status == CODE_SUCCEED) {
                return result.getData();
            }
            throw new ServerException(status, result.getMessage());
        });
    }
}