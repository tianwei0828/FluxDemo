package com.tw.fluxlib.utils.app

import android.content.Context
import android.widget.Toast
import com.tw.utils.base.ObjectUtil

/**
 * Created by wei.tian
 * 2018/5/16
 */
class ToastUtil {
    companion object {
        private const val INTERNAL = 2000
        private var toast: Toast? = null
        private var oldMsg: String? = null
        private var pre: Long = 0L
        private var now: Long = 0L
        fun showShort(context: Context, msg: String) {
            if (ObjectUtil.isNull(toast)) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
                toast!!.show()
                oldMsg = msg
                pre = System.currentTimeMillis()
            } else {
                now = System.currentTimeMillis()
                toast!!.duration = Toast.LENGTH_SHORT
                if (msg == oldMsg) {
                    if (now - pre > INTERNAL) {
                        toast!!.show()
                    }
                } else {
                    toast!!.setText(msg)
                    toast!!.show()
                    oldMsg = msg
                }
                pre = now
            }
        }

        fun showLong(context: Context, msg: String) {
            if (ObjectUtil.isNull(toast)) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
                toast!!.show()
                oldMsg = msg
                pre = System.currentTimeMillis()
            } else {
                now = System.currentTimeMillis()
                toast!!.duration = Toast.LENGTH_LONG
                if (msg == oldMsg) {
                    if (now - pre > INTERNAL) {
                        toast!!.show()
                    }
                } else {
                    toast!!.setText(msg)
                    toast!!.show()
                    oldMsg = msg
                }
                pre = now
            }
        }
    }
}