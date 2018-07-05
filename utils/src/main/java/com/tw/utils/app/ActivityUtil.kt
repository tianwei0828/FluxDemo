package com.tw.fluxlib.utils.app

import android.app.Activity
import android.content.Intent

/**
 * Created by wei.tian
 * 2018/5/14
 */
class ActivityUtil {
    companion object {
        const val REQUEST_CODE_NO = Int.MIN_VALUE
        const val BUNDLE_DATA = "bundle_data"
        const val SERIALIZABLE_DATA = "serializable_data"
        private val activityStack = mutableListOf<Activity>()
        fun pushActivity(activity: Activity) {
            if (!activityStack.contains(activity))
                activityStack.add(activity)
        }

        fun popActivity(activity: Activity) {
            if (activityStack.contains(activity))
                activityStack.remove(activity)
        }

        fun finishActivity(activity: Activity) {
            activity.finish()
            popActivity(activity)
        }

        fun finishAllActivities() {
            for (activity in activityStack) {
                finishActivity(activity)
            }
        }

        fun <T> launchActivity(activity: Activity, targetActivity: Class<T>, requestCode: Int = REQUEST_CODE_NO) {
            if (requestCode == REQUEST_CODE_NO) {
                activity.startActivity(Intent(activity, targetActivity))
            } else {
                activity.startActivityForResult(Intent(activity, targetActivity), requestCode)
            }
        }

        fun launchActivity(activity: Activity, intent: Intent, requestCode: Int = REQUEST_CODE_NO) {
            if (requestCode == REQUEST_CODE_NO) {
                activity.startActivity(intent)
            } else {
                activity.startActivityForResult(intent, requestCode)
            }
        }
    }
}