package com.tw.net.internal;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;
import com.tw.net.CustomException;
import com.tw.net.RxException;
import com.tw.net.ServerException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateEncodingException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;


/**
 * Created by tianwei on 17/2/9.
 */
public class RxErrorHandler {
    private static final String TAG = "RxErrorHandler";

    private RxErrorHandler() {
    }

    private static final RxException RXEXCEPTION = new RxException();

    public static RxException createRxException(Throwable e) {
        Logger.e("createRxException : " + e.getMessage());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            RXEXCEPTION.setCode(code);
            switch (code) {
                case HttpError.UNAUTHORIZED:
                case HttpError.FORBIDDEN:
                case HttpError.NOT_FOUND:
                case HttpError.REQUEST_TIMEOUT:
                    RXEXCEPTION.setMsg(HttpError.ERROR_REQUEST);
                    break;
                case HttpError.GATEWAY_TIMEOUT:
                case HttpError.INTERNAL_SERVER_ERROR:
                case HttpError.BAD_GATEWAY:
                case HttpError.SERVICE_UNAVAILABLE:
                    RXEXCEPTION.setMsg(HttpError.ERROR_SERVER);
                    break;
                case HttpError.ACCESS_DENIED:
                    RXEXCEPTION.setMsg(HttpError.ERROR_RE_LOC);
                    break;
                default:
                    RXEXCEPTION.setMsg(HttpError.ERROR_NET);
                    break;
            }
        } else if (e instanceof SocketTimeoutException) { //超时
            RXEXCEPTION.setCode(DefinedError.CODE_SOCKET_TIMEOUT);
            RXEXCEPTION.setMsg(DefinedError.SOCKET_TIMEOUT);
        } else if (e instanceof ConnectException) { //连接失败
            RXEXCEPTION.setCode(DefinedError.CODE_CONNECT);
            RXEXCEPTION.setMsg(DefinedError.CONNECT);
        } else if (e instanceof SSLHandshakeException) { //证书验证失败
            RXEXCEPTION.setCode(DefinedError.CODE_SSLHANDSHAKE);
            RXEXCEPTION.setMsg(DefinedError.SSLHANDSHAKE);
        } else if (e instanceof CertificateEncodingException) {
            RXEXCEPTION.setCode(DefinedError.CODE_CERTIFICATE_ENCODING);
            RXEXCEPTION.setMsg(DefinedError.CERTIFICATE_ENCODING);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) { //解析错误
            RXEXCEPTION.setCode(DefinedError.CODE_PARSE);
            RXEXCEPTION.setMsg(DefinedError.PARSE);
        } else if (e instanceof ConnectTimeoutException) {
            RXEXCEPTION.setCode(DefinedError.CODE_CONNECT_TIMEOUT);
            RXEXCEPTION.setMsg(DefinedError.CONNECT_TIMEOUT);
        } else if (e instanceof ClassCastException) {
            RXEXCEPTION.setCode(DefinedError.CODE_CLASS_CAST);
            RXEXCEPTION.setMsg(DefinedError.CLASS_CAST);
        } else if (e instanceof CustomException) {
            CustomException ce = (CustomException) e;
            RXEXCEPTION.setCode(ce.getCode());
            RXEXCEPTION.setMsg(ce.getMsg());
        } else if (e instanceof ServerException) {
            ServerException se = (ServerException) e;
            RXEXCEPTION.setCode(se.getCode());
            RXEXCEPTION.setMsg(se.getMsg());
        } else {
            RXEXCEPTION.setCode(DefinedError.CODE_UNDEFINE);
            RXEXCEPTION.setMsg(e.getMessage() == null ? DefinedError.MESSAGE_UNDEFINE : e.getMessage());
        }
        return RXEXCEPTION;
    }

    private class HttpError {
        private static final int UNAUTHORIZED = 401;
        private static final int FORBIDDEN = 403;
        private static final int NOT_FOUND = 404;
        private static final int REQUEST_TIMEOUT = 408;
        private static final int INTERNAL_SERVER_ERROR = 500;
        private static final int BAD_GATEWAY = 502;
        private static final int SERVICE_UNAVAILABLE = 503;
        private static final int GATEWAY_TIMEOUT = 504;
        private static final int ACCESS_DENIED = 302;

        private static final String ERROR_REQUEST = "请求错误";
        private static final String ERROR_SERVER = "服务器错误";
        private static final String ERROR_RE_LOC = "重定向";
        private static final String ERROR_NET = "网络异常";
    }

    private class DefinedError {
        public static final int CODE_UNDEFINE = 590;
        public static final int CODE_SOCKET_TIMEOUT = 490;
        public static final int CODE_CONNECT = 491;
        public static final int CODE_SSLHANDSHAKE = 591;
        public static final int CODE_PARSE = 591;
        public static final int CODE_CERTIFICATE_ENCODING = 592;
        public static final int CODE_CONNECT_TIMEOUT = 492;
        public static final int CODE_CLASS_CAST = 593;

        public static final String MESSAGE_UNDEFINE = "未知错误";
        public static final String SOCKET_TIMEOUT = "网络超时";
        public static final String CONNECT = "连接服务器失败";
        public static final String SSLHANDSHAKE = "证书验证失败";
        public static final String PARSE = "解析错误";
        public static final String CERTIFICATE_ENCODING = "证书路径未找到";
        public static final String CONNECT_TIMEOUT = "连接超时";
        public static final String CLASS_CAST = "请求格式错误";
    }
}
