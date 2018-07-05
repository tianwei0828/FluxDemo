package com.tw.fluxdemo.component.common.activities

import android.content.Intent
import android.os.Bundle
import com.tw.fluxdemo.BuildConfig
import com.tw.fluxdemo.component.common.views.SplashView
import com.tw.fluxdemo.utils.ActivityUtil
import com.tw.utils.app.PermissionsUtil
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashActivity : AbstractActivity(), PermissionsUtil.IPermissionsCallback {

    private lateinit var permissionsUtil: PermissionsUtil
    private val REQUEST_CODE = 100
    private var disposable: Disposable? = null
    override fun initToolBar() {
        toolBar.hide()
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val splashView = SplashView(this)
        addView(splashView)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        requestPermission()
    }

    private fun requestPermission() {
        permissionsUtil = PermissionsUtil.with(this)
                .permissions(PermissionsUtil.Permission.Storage.WRITE_EXTERNAL_STORAGE)
                .isDebug(BuildConfig.DEBUG)
                .requestCode(REQUEST_CODE)
                .request()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionsUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //监听跳转到权限设置界面后再回到应用
        permissionsUtil.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPermissionsGranted(requestCode: Int, vararg permission: String?) {
        launchHomeActivity()
    }

    override fun onPermissionsDenied(requestCode: Int, vararg permission: String?) {
        finish()
    }

    override fun releaseResource() {
        if (!disposable?.isDisposed!!) {
            disposable!!.dispose()
            disposable = null
        }
    }

    private fun launchHomeActivity() {
        disposable = Completable.timer(3, TimeUnit.SECONDS)
                .subscribe {
                    ActivityUtil.launchHome(this)
                    finish()
                }
    }
}
