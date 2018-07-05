package com.tw.fluxdemo.component.home.store

import com.tw.common.exception.FluxException
import com.tw.fluxdemo.actions.ActionType
import com.tw.fluxdemo.callbacks.WeatherInfoCallback
import com.tw.fluxdemo.component.home.repository.HomeRepository
import com.tw.fluxdemo.datas.WeatherInfo
import com.tw.fluxdemo.events.WeatherInfoEvent
import com.tw.fluxlib.events.FluxExceptionEvent
import com.tw.fluxlib.stores.BaseStore

/**
 * Created by wei.tian
 * 2018/7/5
 */
class HomeStore : BaseStore() {
    private var homeRepository: HomeRepository? = null
    override fun register() {
        super.register()
        //初始化HomeRepository
        homeRepository = HomeRepository()
    }

    override fun unregister() {
        super.unregister()
        //销毁HomeRepository
        homeRepository!!.destroy()
        homeRepository = null
    }

    override fun <T> onActionDispatch(type: Int, data: T?) {
        when (type) {
            //接收到GET_WEATHER_INFO Action后从homeRepository获取天气信息
            ActionType.GET_WEATHER_INFO -> {
                homeRepository!!.getWeatherInfo(data as String, object : WeatherInfoCallback {
                    override fun onSuccess(weatherInfo: WeatherInfo) {
                        //获取天气信息成功，发射天气信息
                        postDataEvent(WeatherInfoEvent(weatherInfo))
                    }

                    override fun onError(fluxException: FluxException) {
                        //获取天气信息失败，发射失败信息
                        postDataEvent(FluxExceptionEvent(fluxException))
                    }
                })
            }
        }
    }
}