package com.tw.common.exception

/**
 * Created by wei.tian
 * 2018/7/5
 */
data class FluxException(val msg: String, val userMessage: String, val code: Int = Int.MIN_VALUE) : Throwable(msg)