package com.tw.fluxlib.component.activities

import android.os.Bundle
import android.widget.LinearLayout
import com.tw.fluxlib.component.views.View

/**
 * Created by wei.tian
 * 2018/5/14
 */
interface Activity {
    /**
     * 添加view到跟布局,默认match_parent
     * @param view
     */
    fun addView(view: View)

    /**
     * 添加view到跟布局,并指定layoutParams
     * @param view
     * @param layoutParams
     */
    fun addView(view: View, layoutParams: LinearLayout.LayoutParams)

    /**
     * 初始化view
     * @param savedInstanceState
     */
    fun initView(savedInstanceState: Bundle?)

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    fun initData(savedInstanceState: Bundle?)

    /**
     * 释放资源
     */
    fun releaseResource()
}