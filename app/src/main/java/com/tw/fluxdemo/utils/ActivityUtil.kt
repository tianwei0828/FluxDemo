package com.tw.fluxdemo.utils

import android.app.Activity
import com.tw.fluxdemo.component.home.activities.HomeActivity
import com.tw.fluxlib.utils.app.ActivityUtil

/**
 * Created by wei.tian
 * 2018/5/15
 */
class ActivityUtil {
    companion object {
        fun launchHome(activity: Activity) {
            ActivityUtil.launchActivity(activity, HomeActivity::class.java)
        }
    }
}