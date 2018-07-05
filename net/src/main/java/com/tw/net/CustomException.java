package com.tw.net;

/**
 * Created by tianwei on 17/2/21.
 */
public class CustomException extends Exception {
    private int mCode;
    private String mMsg;

    public CustomException() {
        super();
    }

    public CustomException(int code, String msg) {
        super(msg);
        mCode = code;
        mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public void setMsg(String msg) {
        this.mMsg = msg;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "mCode=" + mCode +
                ", mMsg='" + mMsg + '\'' +
                "} " + super.toString();
    }
}
