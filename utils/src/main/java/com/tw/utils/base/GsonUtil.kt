package com.tw.utils.base

import com.google.gson.Gson

/**
 * Created by wei.tian
 * 2018/7/5
 */
class GsonUtil {
    companion object {
        private val gson = Gson()
        fun getGson(): Gson {
            return gson
        }
    }
}