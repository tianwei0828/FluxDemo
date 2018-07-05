package com.tw.fluxlib.component.repository

import com.tw.utils.app.RxJavaUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by wei.tian
 * 2018/5/16
 */
open class BaseRepository : Repository {
    private var compositeDisposable: CompositeDisposable? = null

    init {
        compositeDisposable = RxJavaUtil.getCompositeDisposable(compositeDisposable)
    }

    override fun add(disposable: Disposable) {
        if (disposable.isDisposed) {
            return
        }
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = RxJavaUtil.getCompositeDisposable(compositeDisposable)
        }
        compositeDisposable!!.add(disposable)
    }

    private fun clear() {
        compositeDisposable?.clear()
        compositeDisposable = null
    }

    override fun destroy() {
        clear()
    }
}