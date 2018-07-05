package com.tw.fluxdemo.component.home.views

import android.app.Activity
import android.widget.Button
import android.widget.TextView
import com.orhanobut.logger.Logger
import com.tw.fluxdemo.R
import com.tw.fluxdemo.actions.ActionType
import com.tw.fluxdemo.datas.WeatherInfo
import com.tw.fluxdemo.events.WeatherInfoEvent
import com.tw.fluxdemo.utils.RxView
import com.tw.fluxlib.actions.ActionCreator
import com.tw.fluxlib.component.views.BaseView
import com.tw.fluxlib.events.FluxExceptionEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by wei.tian
 * 2018/7/5
 */
class HomeView(activity: Activity) : BaseView(activity) {
    override fun getViewLayoutId(): Int {
        //加载布局
        return R.layout.activity_home
    }

    private val btnGetWeatherInfo = realView.findViewById<Button>(R.id.btnGetWeatherInfo)
    private val tvWeatherInfo = realView.findViewById<TextView>(R.id.tvWeatherInfo)

    init {
        //设置点击查询天气按钮的监听
        RxView.clicksThrottle1s(btnGetWeatherInfo)
                .subscribe {
                    //发送GET_WEATHER_INFO Action
                    ActionCreator.createAction(ActionType.GET_WEATHER_INFO, "上海")
                    showLoading()
                }
    }

    private fun showWeatherInfo(weatherInfo: WeatherInfo) {
        tvWeatherInfo.text = weatherInfo.toString()
    }

    //查询天气成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onWeatherInfoEvent(event: WeatherInfoEvent) {
        Logger.e("onWeatherInfoEvent event: $event")
        hideLoading()
        showWeatherInfo(event.weatherInfo)
    }

    //查询天气失败
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFluxExceptionEvent(event: FluxExceptionEvent) {
        Logger.e("onFluxExceptionEvent event: $event")
        hideLoading()
        showToastShort(event.fluxException.userMessage)
    }
}