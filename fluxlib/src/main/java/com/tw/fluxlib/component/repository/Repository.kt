package com.tw.fluxlib.component.repository

import io.reactivex.disposables.Disposable

/**
 * Created by wei.tian
 * 2018/5/16
 */
interface Repository {
    fun add(disposable: Disposable)
    fun destroy()
}