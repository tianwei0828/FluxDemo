package com.tw.fluxlib.stores

import com.orhanobut.logger.Logger
import com.tw.fluxlib.actions.Action
import com.tw.fluxlib.actions.BaseActionType
import com.tw.fluxlib.bus.Bus
import com.tw.fluxlib.events.BaseDataEvent
import com.tw.fluxlib.events.BaseEmptyEvent

/**
 * Created by wei.tian
 * 2018/5/14
 */
abstract class BaseStore : Store {
    private var registered = false
    override fun register() {
        registered = true
        Logger.e("${this.javaClass.simpleName} register: $registered")
    }

    override fun unregister() {
        registered = false
        Logger.e("${this.javaClass.simpleName} unregister: $registered")
    }

    override fun isRegistered(): Boolean {
        return registered
    }

    override fun <T> onDispatch(action: Action<T>) {
        val type = action.type
        val data = action.data
        Logger.e("${this.javaClass.simpleName} onDispatch action: $action")
        Logger.e("${this.javaClass.simpleName} isRegistered: ${isRegistered()}")
        when (type) {
            BaseActionType.REGISTER -> if (data == this && !isRegistered()) register()
            BaseActionType.UNREGISTER -> if (data == this && isRegistered()) unregister()
            BaseActionType.UNREGISTER_ALL -> if (isRegistered()) unregister()
            else -> if (isRegistered()) onActionDispatch(type, data)
        }
    }

    override fun <T> postDataEvent(dataEvent: BaseDataEvent<T>) {
        Logger.e("${this.javaClass.simpleName} postDataEvent: $dataEvent")
        Bus.getEventBus().post(dataEvent)
    }

    override fun postEmptyEvent(emptyEvent: BaseEmptyEvent) {
        Logger.e("${this.javaClass.simpleName} postEmptyEvent: $emptyEvent")
        Bus.getEventBus().post(emptyEvent)
    }

    override fun <T> postStickyDataEvent(dataEvent: BaseDataEvent<T>) {
        Logger.e("${this.javaClass.simpleName} postStickyDataEvent: $dataEvent")
        Bus.getEventBus().postSticky(dataEvent)
    }

    override fun postStickyEmptyEvent(emptyEvent: BaseEmptyEvent) {
        Logger.e("${this.javaClass.simpleName} postEmptyEvent: $emptyEvent")
        Bus.getEventBus().postSticky(emptyEvent)
    }
}