package com.tw.fluxdemo.component.common.views

import android.app.Activity
import com.tw.fluxdemo.R
import com.tw.fluxlib.component.views.BaseView

/**
 * Created by wei.tian
 * 2018/7/2
 */
class SplashView(activity: Activity) : BaseView(activity) {
    override fun getViewLayoutId(): Int {
        return R.layout.activity_splash
    }
}