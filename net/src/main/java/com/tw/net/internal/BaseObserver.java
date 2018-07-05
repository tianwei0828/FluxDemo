package com.tw.net.internal;


import com.tw.net.RxException;

/**
 * Created by wei.tian
 * 2017/9/18
 */

public interface BaseObserver {
    void onError(RxException e);
}
