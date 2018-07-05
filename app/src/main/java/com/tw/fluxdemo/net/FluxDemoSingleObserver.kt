package com.tw.fluxdemo.net

import com.tw.net.BaseSingleObserver
import com.tw.net.RxException

/**
 * Created by wei.tian
 * 2018/7/5
 */
open class FluxDemoSingleObserver<T>: BaseSingleObserver<T>(){
    override fun onSuccess(t: T) = Unit

    override fun onError(e: RxException) = Unit
}