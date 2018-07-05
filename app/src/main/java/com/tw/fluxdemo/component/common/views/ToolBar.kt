package com.tw.fluxdemo.component.common.views

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tw.fluxdemo.R
import com.tw.fluxdemo.utils.RxView
import com.tw.fluxlib.component.views.BaseView

/**
 * ToolBar
 * Created by wei.tian
 * 2018/5/15
 */
class ToolBar(activity: Activity) : BaseView(activity) {
    private val tvTitle: TextView = realView.findViewById(R.id.tvTitle)
    private val ivBack: ImageView = realView.findViewById(R.id.ivBack)

    override fun getViewLayoutId(): Int {
        return R.layout.view_common_toolbar
    }

    init {
        setOnBackClickListener(activity)
    }

    fun hide() {
        if (realView.visibility != View.GONE)
            realView.visibility = View.GONE
    }

    fun show() {
        if (realView.visibility != View.VISIBLE)
            realView.visibility = View.VISIBLE
    }

    fun setTitle(text: String) {
        if (realView.visibility == View.VISIBLE)
            tvTitle.text = text
    }

    fun showBack() {
        if (realView.visibility == View.VISIBLE) {
            if (ivBack.visibility != View.VISIBLE) {
                ivBack.visibility = View.VISIBLE
            }
        }
    }

    fun hideBack() {
        if (realView.visibility == View.VISIBLE) {
            if (ivBack.visibility != View.GONE) {
                ivBack.visibility = View.GONE
            }
        }
    }

    private fun setOnBackClickListener(activity: Activity) {
        RxView.clicksThrottle1s(ivBack)
                .subscribe({ activity.finish() })
    }
}