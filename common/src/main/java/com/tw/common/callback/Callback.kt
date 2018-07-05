package com.tw.appcommon.callback

import com.tw.common.exception.FluxException


/**
 * Created by wei.tian
 * 2018/5/16
 */
interface Callback<in T> {
    fun onSuccess(data: T)
    fun onError(fluxException: FluxException)
}