package com.tw.net;

import android.content.Context;

import com.tw.net.internal.Params;
import com.tw.utils.base.ArrayUtil;
import com.tw.utils.base.ObjectUtil;
import com.tw.utils.base.StringUtil;
import com.tw.utils.base.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import com.tw.net.internal.HttpHeaders;
import retrofit2.Retrofit;

/**
 * Created by wei.tian
 * 2017/9/16
 */

public final class Options {
    private final Context mContext;
    private final boolean mRetryOnConnectionFailure;
    private final long mConnectTimeout;
    private final long mReadTimeout;
    private final long mWriteTimeout;
    private final String[] mHosts;
    private final int[] mCertificates;
    private final String mBaseUrl;
    private final boolean mDebug;
    private final HttpHeaders mHttpHeaders;
    private final Params mParams;
    private final List<Interceptor> mInterceptors;
    private final List<Interceptor> mNetworkInterceptors;
    private final Retrofit mRetrofit;

    public Options(Builder builder) {
        mContext = builder.mContext;
        mRetryOnConnectionFailure = builder.mRetryOnConnectionFailure;
        mConnectTimeout = builder.mConnectTimeout;
        mReadTimeout = builder.mReadTimeout;
        mWriteTimeout = builder.mWriteTimeout;
        mHosts = builder.mHosts;
        mCertificates = builder.mCertificates;
        mBaseUrl = builder.mBaseUrl;
        mDebug = builder.mDebug;
        mHttpHeaders = builder.mHttpHeaders;
        mParams = builder.mParams;
        mInterceptors = builder.mInterceptors;
        mNetworkInterceptors = builder.mNetworkInterceptors;
        mRetrofit = builder.mRetrofit;
    }

    public static final class Builder {
        private Context mContext;
        private boolean mRetryOnConnectionFailure = true;
        private long mConnectTimeout = 10_000;
        private long mReadTimeout = 10_000;
        private long mWriteTimeout = 10_000;
        private String[] mHosts;
        private int[] mCertificates;
        private String mBaseUrl;
        private boolean mDebug = false;
        private HttpHeaders mHttpHeaders;
        private Params mParams;
        private List<Interceptor> mInterceptors = new ArrayList<>();
        private List<Interceptor> mNetworkInterceptors = new ArrayList<>();
        private Retrofit mRetrofit;

        public Builder context(Context context) {
            mContext = ObjectUtil.requireNonNull(context, "context == null");
            return this;
        }

        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            mRetryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder connectTimeout(int connectTimeout, TimeUnit timeUnit) {
            mConnectTimeout = TimeUtil.checkDuration("connectTimeout", connectTimeout, timeUnit);
            return this;
        }

        public Builder readTimeout(int readTimeout, TimeUnit timeUnit) {
            mReadTimeout = TimeUtil.checkDuration("readTimeout", readTimeout, timeUnit);
            return this;
        }

        public Builder writeTimeout(int writeTimeout, TimeUnit timeUnit) {
            mWriteTimeout = TimeUtil.checkDuration("writeTimeout", writeTimeout, timeUnit);
            return this;
        }

        public Builder hosts(String[] hosts) {
            mHosts = ArrayUtil.requireNotEmpty(hosts, "hosts are empty");
            return this;
        }

        public Builder certificates(int[] certificates) {
            mCertificates = ArrayUtil.requireNotEmpty(certificates, "certificates are empty");
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            mBaseUrl = StringUtil.requireNotBlank(baseUrl, "baseUrl is blank");
            return this;
        }

        public Builder debug(boolean debug) {
            mDebug = debug;
            return this;
        }

        public Builder httpHeaders(HttpHeaders httpHeaders) {
            mHttpHeaders = ObjectUtil.requireNonNull(httpHeaders, "httpHeaders == null");
            return this;
        }

        public Builder params(Params params) {
            mParams = ObjectUtil.requireNonNull(params, "params == null");
            return this;
        }

        public Builder interceptor(Interceptor interceptor) {
            ObjectUtil.checkNonNull(interceptor, "interceptor == null");
            mInterceptors.add(interceptor);
            return this;
        }

        public Builder networkInterceptor(Interceptor networkInterceptor) {
            ObjectUtil.checkNonNull(networkInterceptor, "networkInterceptor == null");
            mNetworkInterceptors.add(networkInterceptor);
            return this;
        }

        public Builder retrofit(Retrofit retrofit) {
            mRetrofit = ObjectUtil.requireNonNull(retrofit, "retrofit == null");
            return this;
        }

        public Options build() {
            return new Options(this);
        }


    }

    public Context getContext() {
        return mContext;
    }

    public boolean isRetryOnConnectionFailure() {
        return mRetryOnConnectionFailure;
    }

    public long getConnectTimeout() {
        return mConnectTimeout;
    }

    public long getReadTimeout() {
        return mReadTimeout;
    }

    public long getWriteTimeout() {
        return mWriteTimeout;
    }

    public String[] getHosts() {
        return mHosts;
    }

    public int[] getCertificates() {
        return mCertificates;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public boolean isDebug() {
        return mDebug;
    }

    public HttpHeaders getHttpHeaders() {
        return mHttpHeaders;
    }

    public Params getParams() {
        return mParams;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    public List<Interceptor> getNetworkInterceptors() {
        return mNetworkInterceptors;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
