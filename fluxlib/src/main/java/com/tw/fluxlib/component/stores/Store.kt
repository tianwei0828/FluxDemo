package com.tw.fluxlib.stores

import com.tw.fluxlib.actions.Action
import com.tw.fluxlib.events.BaseDataEvent
import com.tw.fluxlib.events.BaseEmptyEvent


/**
 * Created by wei.tian
 * 2018/5/14
 */
interface Store {
    fun register()
    fun isRegistered(): Boolean
    fun unregister()
    fun <T> onDispatch(action: Action<T>)
    fun <T> onActionDispatch(type: Int, data: T?)
    fun <T> postDataEvent(dataEvent: BaseDataEvent<T>)
    fun postEmptyEvent(emptyEvent: BaseEmptyEvent)
    fun <T> postStickyDataEvent(dataEvent: BaseDataEvent<T>)
    fun postStickyEmptyEvent(emptyEvent: BaseEmptyEvent)
}