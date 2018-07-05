package com.tw.fluxdemo.component.home.repository

import com.tw.common.exception.FluxException
import com.tw.fluxdemo.callbacks.WeatherInfoCallback
import com.tw.fluxdemo.datas.WeatherInfo
import com.tw.fluxdemo.models.Weather
import com.tw.fluxdemo.net.FluxDemoSingleObserver
import com.tw.fluxlib.component.repository.BaseRepository
import com.tw.net.RxException
import io.reactivex.disposables.Disposable

/**
 * Created by wei.tian
 * 2018/7/5
 */
class HomeRepository : BaseRepository() {
    //初始化天气的Model
    private val weather = Weather()
    fun getWeatherInfo(city: String, weatherInfoCallback: WeatherInfoCallback) {
        //调用model的方法，进行网络请求
        weather.getWeatherInfo(city)
                .subscribe(object : FluxDemoSingleObserver<WeatherInfo>() {
                    override fun onSubscribe(d: Disposable) {
                        super.onSubscribe(d)
                        add(d)
                    }

                    override fun onSuccess(t: WeatherInfo) {
                        weatherInfoCallback.onSuccess(t)
                    }

                    override fun onError(e: RxException) {
                        weatherInfoCallback.onError(FluxException(e.msg, "获取天气信息失败,请重试", e.code))
                    }
                })
    }
}