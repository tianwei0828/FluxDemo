package com.tw.fluxdemo.utils

import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import com.tw.fluxdemo.FluxDemoApp
import com.tw.fluxdemo.R
import com.tw.fluxlib.utils.app.ToastUtil
import com.tw.utils.base.NetUtil
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by wei.tian
 * 2018/5/22
 */
class RxView {
    companion object {
        fun clicksThrottle1s(view: View): Observable<Any> {
            return clicksThrottle(view, 1, TimeUnit.SECONDS)
        }

        fun clicksThrottle(view: View, windowDuration: Long, timeUnit: TimeUnit): Observable<Any> {
            return RxView.clicks(view).throttleFirst(windowDuration, timeUnit)
        }

        fun editorActionsThrottle1s(textView: TextView): Observable<Int> {
            return editorActionsThrottle(textView, 1, TimeUnit.SECONDS)
        }

        fun editorActionsThrottle(textView: TextView, windowDuration: Long, timeUnit: TimeUnit): Observable<Int> {
            return RxTextView.editorActions(textView).throttleFirst(windowDuration, timeUnit)
        }

        fun clicksThrottle1sWithNetJudge(view: View): Observable<Any> {
            return clicksThrottleWithNetJudge(view, 1, TimeUnit.SECONDS)
        }

        fun clicksThrottleWithNetJudge(view: View, windowDuration: Long, timeUnit: TimeUnit): Observable<Any> {
            return clicksThrottle(view, windowDuration, timeUnit).filter {
                val netConnected = NetUtil.isNetConnected(FluxDemoApp.appContext())
                if (!netConnected) {
                    ToastUtil.showShort(FluxDemoApp.appContext(), FluxDemoApp.appContext().getString(R.string.net_no_connected_tips))
                }
                netConnected
            }
        }

        fun editorActionsThrottle1sWithNetJudge(textView: TextView): Observable<Int> {
            return editorActionsThrottleWithNetJudge(textView, 1, TimeUnit.SECONDS)
        }

        fun editorActionsThrottleWithNetJudge(textView: TextView, windowDuration: Long, timeUnit: TimeUnit): Observable<Int> {
            return editorActionsThrottle(textView, windowDuration, timeUnit).filter {
                val netConnected = NetUtil.isNetConnected(FluxDemoApp.appContext())
                if (!netConnected) {
                    ToastUtil.showShort(FluxDemoApp.appContext(), FluxDemoApp.appContext().getString(R.string.net_no_connected_tips))
                }
                netConnected
            }
        }

        fun checkedChangesThrottle(view: CompoundButton, windowDuration: Long, timeUnit: TimeUnit): Observable<Boolean> {
            return RxCompoundButton.checkedChanges(view).throttleFirst(windowDuration, timeUnit)
        }
    }
}