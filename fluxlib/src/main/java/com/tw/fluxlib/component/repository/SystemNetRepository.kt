package com.tw.fluxlib.component.repository

import com.tw.fluxlib.BaseApp
import com.tw.utils.base.NetUtil


/**
 * Created by wei.tian
 * 2018/6/4
 */
class SystemNetRepository : BaseRepository() {

    fun registerNetConnChangedReceiver() {
        NetUtil.registerNetConnChangedReceiver(BaseApp.appContext())
    }

    fun addNetChangedListener(netConnChangedListener: NetUtil.NetConnChangedListener) {
        NetUtil.addNetConnChangedListener(netConnChangedListener)
    }

    fun removeNetChangedListener(netConnChangedListener: NetUtil.NetConnChangedListener) {
        NetUtil.removeNetConnChangedListener(netConnChangedListener)
    }

    fun unregisterNetConnChangedReceiver() {
        NetUtil.unregisterNetConnChangedReceiver(BaseApp.appContext())
    }
}