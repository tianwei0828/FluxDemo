package com.tw.net.config;

import android.content.Context;

import com.tw.net.internal.HttpHeaders;
import com.tw.net.internal.Params;

import java.util.List;

import okhttp3.Interceptor;
import retrofit2.Retrofit;

/**
 * Created by wei.tian
 * 2017/9/16
 */

public final class Config {
    private static Context sContext;
    private static boolean sRetryOnConnectionFailure;
    private static long sConnectTimeout;
    private static long sReadTimeout;
    private static long sWriteTimeout;
    private static String[] sHosts;
    private static int[] sCertificates;
    private static String sBaseUrl;
    private static boolean sDebug;
    private static HttpHeaders sHttpHeaders;
    private static Params sParams;
    private static List<Interceptor> sInterceptors;
    private static List<Interceptor> sNetworkInterceptors;
    private static Retrofit sRetrofit;

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context) {
        sContext = context;
    }

    public static boolean isRetryOnConnectionFailure() {
        return sRetryOnConnectionFailure;
    }

    public static void setsRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
        sRetryOnConnectionFailure = retryOnConnectionFailure;
    }

    public static long getConnectTimeout() {
        return sConnectTimeout;
    }

    public static void setConnectTimeout(long connectTimeout) {
        sConnectTimeout = connectTimeout;
    }

    public static long getReadTimeout() {
        return sReadTimeout;
    }

    public static void setReadTimeout(long readTimeout) {
        sReadTimeout = readTimeout;
    }

    public static long getWriteTimeout() {
        return sWriteTimeout;
    }

    public static void setWriteTimeout(long writeTimeout) {
        sWriteTimeout = writeTimeout;
    }

    public static String[] getHosts() {
        return sHosts;
    }

    public static void setHosts(String[] hosts) {
        sHosts = hosts;
    }

    public static int[] getCertificates() {
        return sCertificates;
    }

    public static void setCertificates(int[] certificates) {
        sCertificates = certificates;
    }

    public static String getBaseUrl() {
        return sBaseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        sBaseUrl = baseUrl;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void debug(boolean debug) {
        sDebug = debug;
    }

    public static void setHttpHeaders(HttpHeaders httpHeaders) {
        sHttpHeaders = httpHeaders;
    }

    public static HttpHeaders getHttpHeaders() {
        return sHttpHeaders;
    }

    public static Params getParams() {
        return sParams;
    }

    public static void setParams(Params params) {
        sParams = params;
    }

    public static List<Interceptor> getInterceptors() {
        return sInterceptors;
    }

    public static void setInterceptors(List<Interceptor> interceptors) {
        sInterceptors = interceptors;
    }

    public static List<Interceptor> getNetworkInterceptors() {
        return sNetworkInterceptors;
    }

    public static void setNetworkInterceptors(List<Interceptor> networkInterceptors) {
        sNetworkInterceptors = networkInterceptors;
    }

    public static void setRetrofit(Retrofit retrofit) {
        sRetrofit = retrofit;
    }

    public static Retrofit getRetrofit() {
        return sRetrofit;
    }
}