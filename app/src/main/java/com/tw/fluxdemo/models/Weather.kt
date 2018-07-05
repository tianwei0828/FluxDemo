package com.tw.fluxdemo.models

import com.tw.fluxdemo.api.WeatherApi
import com.tw.fluxdemo.datas.WeatherInfo
import com.tw.net.HttpRequest
import com.tw.net.utils.NetRxJavaUtil
import com.tw.utils.app.RxJavaUtil
import io.reactivex.Single

/**
 * 获取天气的Model
 * Created by wei.tian
 * 2018/7/5
 */
class Weather {
    //获取天气信息的Single流
    fun getWeatherInfo(city: String): Single<WeatherInfo> {
        return HttpRequest.create(WeatherApi::class.java)
                .getWeatherInfo(city)
                .compose(RxJavaUtil.applySingleMainSchedulers())
                .compose(NetRxJavaUtil.applySingleFeedTransformer())
    }
}