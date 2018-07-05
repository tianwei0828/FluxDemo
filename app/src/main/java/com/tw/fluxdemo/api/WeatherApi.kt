package com.tw.fluxdemo.api

import com.tw.fluxdemo.datas.WeatherInfo
import com.tw.net.Feed
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wei.tian
 * 2018/7/5
 */
interface WeatherApi {
    @GET("open/api/weather/json.shtml")
    fun getWeatherInfo(@Query("city") city: String): Single<Feed<WeatherInfo>>
}