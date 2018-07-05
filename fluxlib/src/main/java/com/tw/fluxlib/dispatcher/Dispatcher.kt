package com.tw.fluxlib.dispatcher

import com.orhanobut.logger.Logger
import com.tw.fluxlib.actions.Action
import com.tw.fluxlib.actions.BaseActionType
import com.tw.fluxlib.stores.Store
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by wei.tian
 * 2018/5/14
 */
object Dispatcher {
    private val stores = mutableListOf<Store>()

    private fun register(store: Store) {
        if (!stores.contains(store))
            stores.add(store)
    }

    private fun unregister(store: Store) {
        if (stores.contains(store))
            stores.remove(store)
    }

    private fun unregisterAll() {
        stores.clear()
    }

    fun <T> dispatch(action: Action<T>) {
        Logger.e("dispatch: $action")
        val type = action.type
        val data = action.data
        if (type == BaseActionType.REGISTER && data is Store) {
            register(data)
        }
        Logger.e("stores: $stores")
        Observable.fromIterable(stores)
                .subscribeBy(
                        onNext = { it.onDispatch(action) },
                        onComplete = {
                            when (type) {
                                BaseActionType.UNREGISTER -> if (data is Store) unregister(data)
                                BaseActionType.UNREGISTER_ALL -> unregisterAll()
                            }
                        })
    }
}