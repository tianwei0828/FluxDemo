package com.tw.fluxdemo.events

import com.tw.fluxdemo.datas.WeatherInfo
import com.tw.fluxlib.events.BaseDataEvent

/**
 * Created by wei.tian
 * 2018/7/5
 */
data class WeatherInfoEvent(val weatherInfo: WeatherInfo) : BaseDataEvent<WeatherInfo>(weatherInfo)