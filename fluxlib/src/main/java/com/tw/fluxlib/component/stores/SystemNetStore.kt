package com.tw.fluxlib.component.stores

import com.tw.fluxlib.actions.BaseActionType
import com.tw.fluxlib.component.repository.SystemNetRepository
import com.tw.fluxlib.events.NetConnectedEvent
import com.tw.fluxlib.events.NoNetConnectedEvent
import com.tw.fluxlib.stores.BaseStore
import com.tw.utils.base.NetUtil

/**
 * Created by wei.tian
 * 2018/6/11
 */
class SystemNetStore : BaseStore() {
    private val netConnChangedListener: NetUtil.NetConnChangedListener = NetUtil.NetConnChangedListener {
        when (it) {
            NetUtil.ConnectStatus.MOBILE,
            NetUtil.ConnectStatus.MOBILE_2G,
            NetUtil.ConnectStatus.MOBILE_3G,
            NetUtil.ConnectStatus.MOBILE_4G,
            NetUtil.ConnectStatus.WIFI -> if (isRegistered()) postEmptyEvent(NetConnectedEvent())
            else -> if (isRegistered()) postEmptyEvent(NoNetConnectedEvent())
        }
    }
    private var systemNetRepository: SystemNetRepository? = null

    override fun register() {
        super.register()
        systemNetRepository = SystemNetRepository()
    }

    override fun unregister() {
        super.unregister()
        systemNetRepository!!.destroy()
        systemNetRepository = null
    }

    private fun registerNetConnChangedReceiver() {
        systemNetRepository!!.registerNetConnChangedReceiver()
    }

    private fun addNetChangedListener() {
        systemNetRepository!!.addNetChangedListener(netConnChangedListener)
    }

    private fun removeNetChangedListener() {
        systemNetRepository!!.removeNetChangedListener(netConnChangedListener)
    }

    private fun unregisterNetConnChangedReceiver() {
        systemNetRepository!!.unregisterNetConnChangedReceiver()
    }

    override fun <T> onActionDispatch(type: Int, data: T?) {
        when (type) {
            BaseActionType.REGISTER_NET_CONN_CHANGED_RECEIVER -> registerNetConnChangedReceiver()
            BaseActionType.ADD_NET_CHANGED_LISTENER -> addNetChangedListener()
            BaseActionType.REMOVE_NET_CHANGED_LISTENER -> removeNetChangedListener()
            BaseActionType.UNREGISTER_NET_CONN_CHANGED_RECEIVER -> unregisterNetConnChangedReceiver()
        }
    }
}