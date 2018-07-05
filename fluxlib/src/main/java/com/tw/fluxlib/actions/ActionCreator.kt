package com.tw.fluxlib.actions

import android.os.Handler
import android.os.Looper
import com.orhanobut.logger.Logger
import com.tw.fluxlib.dispatcher.Dispatcher
import com.tw.fluxlib.stores.Store

/**
 * Action的创建者，所有Action都应由此创建
 * Created by wei.tian
 * 2018/5/14
 */
object ActionCreator {
    private const val MSG_CREATE_THEN_DISPATCH = 0
    private val handler: Handler = Handler(Looper.getMainLooper()) {
        Logger.e("msg: $it")
        when (it.what) {
            MSG_CREATE_THEN_DISPATCH -> Dispatcher.dispatch(Action(it.arg1, it.obj))
        }
        return@Handler false
    }

    fun createAction(actionType: Int) {
        createAction(actionType, null)
    }

    fun <T> createAction(actionType: Int, data: T?) {
        val msg = handler.obtainMessage()
        msg.what = MSG_CREATE_THEN_DISPATCH
        msg.arg1 = actionType
        msg.obj = data
        handler.sendMessage(msg)
    }

    fun createRegisterAction(store: Store) {
        createAction(BaseActionType.REGISTER, store)
    }

    fun createUnregisterAction(store: Store) {
        createAction(BaseActionType.UNREGISTER, store)
    }

    /**
     * 创建取消注册所有的Action
     * 该方法最好在例如:双击退出应用这种场景使用
     */
    fun createUnregisterAllAction() {
        createAction(BaseActionType.UNREGISTER_ALL, null)
    }
}