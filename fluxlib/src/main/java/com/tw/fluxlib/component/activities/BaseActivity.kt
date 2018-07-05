package com.tw.fluxlib.component.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import com.orhanobut.logger.Logger
import com.tw.fluxlib.R
import com.tw.fluxlib.component.views.BaseView
import com.tw.fluxlib.component.views.View
import com.tw.fluxlib.utils.app.ActivityUtil
import kotlinx.android.synthetic.main.activity_base.*


/**
 * Created by wei.tian
 * 2018/5/14
 */
abstract class BaseActivity : AppCompatActivity(), Activity {
    private val views = mutableListOf<View>()
    private fun getActivityLayoutId(): Int {
        return R.layout.activity_base
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestImmersionStatusBar()
        super.onCreate(savedInstanceState)
        Logger.e("${this.javaClass.simpleName} onCreate views: $views")
        ActivityUtil.pushActivity(this)
        val activityLayoutId = getActivityLayoutId()
        if (activityLayoutId <= 0) {
            throw IllegalArgumentException("activityLayoutId <= 0")
        }
        setContentView(activityLayoutId)
        initView(savedInstanceState)
        initData(savedInstanceState)
        Logger.e("${this.javaClass.simpleName} onCreated views: $views")
        for (view in views) {
            view.onCreate()
        }
    }

    private fun requestImmersionStatusBar() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    override fun addView(view: View) {
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(view, layoutParams)
    }

    override fun addView(view: View, layoutParams: LinearLayout.LayoutParams) {
        llBaseRoot.addView((view as BaseView).realView, layoutParams)
        views.add(view)
    }

    override fun onResume() {
        super.onResume()
        Logger.e("${this.javaClass.simpleName} onResume views: $views")
        for (view in views) {
            view.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        Logger.e("${this.javaClass.simpleName} onPause views: $views")
        for (view in views) {
            view.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.e("${this.javaClass.simpleName} onDestroy views: $views")
        for (view in views) {
            view.onDestroy()
        }
        views.clear()
        releaseResource()
        ActivityUtil.popActivity(this)
    }
}