package com.tw.fluxlib.utils.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.util.DisplayMetrics
import android.view.WindowManager
import java.io.File


/**
 * Created by wei.tian
 * 2018/5/24
 */
class AppUtil {
    companion object {
        fun getScreenSize(context: Context): DisplayMetrics {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(dm)
            return dm
        }

        fun getPackageName(context: Context): String {
            return context.packageName
        }

        fun getVersionName(context: Context): String {
            return context.packageManager.getPackageInfo(getPackageName(context), 0).versionName
        }

        fun getVersionCode(context: Context): Int {
            return context.packageManager.getPackageInfo(getPackageName(context), 0).versionCode
        }

        fun installApk(context: Context, apkFile: File) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                val contentUri = FileProvider.getUriForFile(
                        context, "${getPackageName(context)}.fileprovider", apkFile)
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
            } else {
                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
            }
            context.startActivity(intent)
        }
    }
}