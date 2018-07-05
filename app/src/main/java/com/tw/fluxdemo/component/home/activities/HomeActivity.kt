package com.tw.fluxdemo.component.home.activities

import android.os.Bundle
import com.tw.fluxdemo.component.common.activities.AbstractActivity
import com.tw.fluxdemo.component.home.store.HomeStore
import com.tw.fluxdemo.component.home.views.HomeView
import com.tw.fluxlib.actions.ActionCreator

class HomeActivity : AbstractActivity() {
    private lateinit var homeStore: HomeStore
    override fun initToolBar() {
        //设置toolbar相关属性
        toolBar.show()
        toolBar.setTitle("当日天气")
        toolBar.showBack()
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        //初始化HomeView
        val homeView = HomeView(this)
        addView(homeView)
    }

    override fun initData(savedInstanceState: Bundle?) {
        //初始化HomeStore
        homeStore = HomeStore()
        //将homeStore注册到Dispatcher中
        ActionCreator.createRegisterAction(homeStore)
    }

    override fun releaseResource() {
        //页面销毁时，将homeStore从Dispatcher中注销
        ActionCreator.createUnregisterAction(homeStore)
    }
}
