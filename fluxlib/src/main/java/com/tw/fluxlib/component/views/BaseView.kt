package com.tw.fluxlib.component.views

import android.app.Activity
import com.orhanobut.logger.Logger
import com.tw.fluxlib.R
import com.tw.fluxlib.bus.Bus
import com.tw.fluxlib.events.NetConnectedEvent
import com.tw.fluxlib.events.NoNetConnectedEvent
import com.tw.fluxlib.utils.app.ToastUtil
import com.tw.fluxlib.views.LoadingDialog
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by wei.tian
 * 2018/5/15
 */
abstract class BaseView(activity: Activity) : View {
    private var activity = activity
    val realView: android.view.View
    private val loadingDialog = LoadingDialog(activity)

    init {
        val viewLayoutId = getViewLayoutId()
        if (viewLayoutId <= 0) {
            throw IllegalArgumentException("viewLayoutId <= 0")
        }
        realView = android.view.View.inflate(activity, viewLayoutId, null)
    }

    private fun register() {
        if (!Bus.getEventBus().isRegistered(this)) {
            Bus.getEventBus().register(this)
            Logger.e("${this.javaClass.simpleName}: real register")
        }
    }

    private fun unregister() {
        if (Bus.getEventBus().isRegistered(this)) {
            Bus.getEventBus().unregister(this)
            Logger.e("${this.javaClass.simpleName}: real unregister")
        }
    }

    override fun onCreate() {
        Logger.e("${this.javaClass.simpleName}: onCreate")
    }

    override fun onResume() {
        Logger.e("${this.javaClass.simpleName}: onResume")
        register()
    }

    override fun onPause() {
        Logger.e("${this.javaClass.simpleName}: onPause")
        unregister()
    }

    override fun onDestroy() {
        Logger.e("${this.javaClass.simpleName}: onDestroy")
        hideLoading()
    }

    override fun onNetConnected() {
        Logger.e("${this.javaClass.simpleName}: onNetConnected")
    }

    override fun onNoNetConnected() {
        Logger.e("${this.javaClass.simpleName}: onNoNetConnected")
        showToastShort(R.string.net_no_connected_tips)
    }

    override fun showLoading() {
        Logger.e("${this.javaClass.simpleName}: showLoading")
        if (!loadingDialog.isShowing) {
            loadingDialog.show()
        } else {
            hideLoading()
            loadingDialog.show()
        }
    }

    override fun hideLoading() {
        Logger.e("${this.javaClass.simpleName}: hideLoading")
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    override fun showToastShort(resId: Int) {
        ToastUtil.showShort(activity, activity.getString(resId))
    }

    override fun showToastShort(message: String) {
        ToastUtil.showShort(activity, message)
    }

    override fun showToastLong(resId: Int) {
        ToastUtil.showLong(activity, activity.getString(resId))
    }

    override fun showToastLong(message: String) {
        ToastUtil.showLong(activity, message)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onNetConnected(event: NetConnectedEvent) {
        onNetConnected()
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onNoNetConnected(event: NoNetConnectedEvent) {
        onNoNetConnected()
    }
}