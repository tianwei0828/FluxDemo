package com.tw.fluxlib.actions

/**
 * Action
 * Created by wei.tian
 * 2018/5/14
 */
data class Action<T>(val type: Int, val data: T? = null)