package com.tw.net.internal;

import com.orhanobut.logger.Logger;
import com.tw.utils.base.CollectionUtil;
import com.tw.utils.base.StringUtil;

import java.io.IOException;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求时Header拦截器
 * Created by wei.tian on 2017/4/20.
 */

public class HeadersInterceptor extends BaseInterceptor<HttpHeaders> {

    public HeadersInterceptor(@NonNull HttpHeaders httpHeaders) {
        super(httpHeaders);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Logger.d("before inject headers : " + request.headers());
        Request.Builder newBuilder = request.newBuilder();
        injectHeaders(newBuilder);
        injectAddHeaders(newBuilder);
        Request newRequest = newBuilder.build();
        Logger.d("after inject headers : " + newRequest.headers());
        return chain.proceed(newRequest);
    }

    /**
     * 注入唯一Headers
     *
     * @param builder
     */
    private void injectHeaders(Request.Builder builder) {
        Map<String, String> headers = getParams().getHeaders();
        Logger.d("injectHeaders : " + headers);
        if (CollectionUtil.notEmpty(headers)) {
            for (Map.Entry<String, String> me : headers.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.header(key, value);
            }
        }
    }

    /**
     * 注入非唯一Headers
     *
     * @param builder
     */
    private void injectAddHeaders(Request.Builder builder) {
        Map<String, String> addHeaders = getParams().getAddHeaders();
        Logger.d("injectAddHeaders : " + addHeaders);
        if (CollectionUtil.notEmpty(addHeaders)) {
            for (Map.Entry<String, String> me : addHeaders.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.addHeader(key, value);
            }
        }
    }
}
