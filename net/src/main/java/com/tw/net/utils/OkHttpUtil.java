package com.tw.net.utils;


import com.tw.net.config.Config;
import com.tw.net.internal.HeadersInterceptor;
import com.tw.net.internal.Params;
import com.tw.net.internal.ParamsInterceptor;
import com.tw.utils.base.ArrayUtil;
import com.tw.utils.base.CollectionUtil;
import com.tw.utils.base.ObjectUtil;
import com.tw.utils.base.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import com.tw.net.internal.HttpHeaders;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttp 工具类
 * Created by wei.tian on 2017/5/7.
 */

public final class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";

    private OkHttpUtil() {
        throw new IllegalStateException("No instance!");
    }

    static OkHttpClient buildClient() {
        if (Config.isDebug()) {
            return buildDebugClient();
        } else {
            return buildReleaseClient();
        }
    }

    private static OkHttpClient buildDebugClient() {
        return buildReleaseClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static OkHttpClient buildReleaseClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(Config.isRetryOnConnectionFailure())
                .connectTimeout(Config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(Config.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(Config.getWriteTimeout(), TimeUnit.MILLISECONDS);
        HttpHeaders httpHeaders = Config.getHttpHeaders();
        if (ObjectUtil.nonNull(httpHeaders)) {
            builder.addInterceptor(new HeadersInterceptor(httpHeaders));
        }
        Params params = Config.getParams();
        if (ObjectUtil.nonNull(params)) {
            builder.addInterceptor(new ParamsInterceptor(params));
        }
        int[] certificates = Config.getCertificates();
        String[] hosts = Config.getHosts();
        if (ArrayUtil.notEmpty(certificates) && ArrayUtil.notEmpty(hosts)) {
            Object[] sslSocketFactory = HttpsUtil.getSSLSocketFactory(Config.getContext(), certificates);
            if (ArrayUtil.notEmpty(sslSocketFactory)) {
                SSLSocketFactory factory = (SSLSocketFactory) sslSocketFactory[0];
                X509TrustManager manager = (X509TrustManager) sslSocketFactory[1];
                builder.sslSocketFactory(factory, manager);
            }
            HostnameVerifier hostnameVerifier = HttpsUtil.getHostnameVerifier(hosts);
            if (ObjectUtil.nonNull(hostnameVerifier)) {
                builder.hostnameVerifier(hostnameVerifier);
            }
        }
        List<Interceptor> interceptors = Config.getInterceptors();
        if (CollectionUtil.notEmpty(interceptors)) {
            for (Interceptor interceptor : interceptors) {
                if (ObjectUtil.isNull(interceptor)) {
                    continue;
                }
                builder.addInterceptor(interceptor);
            }
        }
        List<Interceptor> networkInterceptors = Config.getNetworkInterceptors();
        if (CollectionUtil.notEmpty(networkInterceptors)) {
            for (Interceptor networkInterceptor : networkInterceptors) {
                if (ObjectUtil.isNull(networkInterceptor)) {
                    continue;
                }
                builder.addNetworkInterceptor(networkInterceptor);
            }
        }
        return builder.build();
    }


    private static final RequestBody DEFAULT_REQUEST_BODY = new FormBody.Builder().build();

    public static RequestBody getDefaultRequestBody() {
        return DEFAULT_REQUEST_BODY;
    }

    /**
     * 根据Json 创建RequestBody
     *
     * @param json
     * @return
     */
    public synchronized static RequestBody createRequestBody(String json) {
        RequestBody requestBody = null;
        if (StringUtil.isBlank(json)) {
            requestBody = getDefaultRequestBody();
        } else if (StringUtil.isNotBlank(json)) {
            requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        }
        return requestBody;
    }

    /**
     * 根据参数创建RequestBody
     *
     * @param params
     * @return
     */
    public synchronized static RequestBody createRequestBody(Map<String, String> params) {
        RequestBody requestBody = null;
        if (CollectionUtil.isEmpty(params)) {
            requestBody = getDefaultRequestBody();
        } else if (CollectionUtil.notEmpty(params)) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> me : params.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.add(key, value);
            }
            requestBody = builder.build();
        }
        return requestBody;
    }

    /**
     * 根据参数创建RequestBody
     *
     * @param json
     * @param params
     * @return
     */
    public synchronized static RequestBody createRequestBody(String json, Map<String, String> params) {
        RequestBody requestBody = null;
        if (StringUtil.isBlank(json) && CollectionUtil.isEmpty(params)) {
            requestBody = new FormBody.Builder().build();
        } else if (StringUtil.isBlank(json) && CollectionUtil.notEmpty(params)) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> me : params.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.add(key, value);
            }
            requestBody = builder.build();
        } else if (StringUtil.isNotBlank(json) && CollectionUtil.isEmpty(params)) {
            requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        } else if (StringUtil.isNotBlank(json) && CollectionUtil.notEmpty(params)) {
            throw new IllegalArgumentException("can't post both json and bodyParams");
        } else {
            throw new IllegalArgumentException("bodyParams has key or value that is null");
        }
        return requestBody;
    }
}
