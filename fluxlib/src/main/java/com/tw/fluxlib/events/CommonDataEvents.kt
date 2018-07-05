package com.tw.fluxlib.events

import com.tw.common.exception.FluxException


/**
 * Created by wei.tian
 * 2018/5/17
 */
data class FluxExceptionEvent(var fluxException: FluxException) : BaseDataEvent<FluxException>(fluxException)