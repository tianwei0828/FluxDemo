package com.tw.fluxlib.component.views

/**
 * Created by wei.tian
 * 2018/5/15
 */
interface View {
    /**
     * 获取view的布局
     *
     * @return 布局id
     */
    fun getViewLayoutId(): Int

    /*生命周期相关*/
    fun onCreate()

    fun onResume()

    fun onPause()

    fun onDestroy()
    /*网络相关*/
    fun onNetConnected()

    fun onNoNetConnected()
    /*loading*/
    fun showLoading()

    fun hideLoading()
    /*toast*/
    fun showToastShort(resId: Int)

    fun showToastShort(message: String)

    fun showToastLong(resId: Int)
    
    fun showToastLong(message: String)
}