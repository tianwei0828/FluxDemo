package com.tw.fluxdemo.component.common.activities

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import com.tw.fluxdemo.R
import com.tw.fluxdemo.component.common.views.ToolBar
import com.tw.fluxlib.component.activities.BaseActivity

/**
 * Created by wei.tian
 * 2018/5/15
 */
abstract class AbstractActivity : BaseActivity() {
    protected lateinit var toolBar: ToolBar
    override fun initView(savedInstanceState: Bundle?) {
        toolBar = ToolBar(this)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, resources.getDimension(R.dimen.common_toolbar_height).toInt())
        addView(toolBar, layoutParams)
        initToolBar()
    }

    abstract fun initToolBar()

    override fun initData(savedInstanceState: Bundle?) = Unit
}