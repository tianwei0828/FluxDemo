package com.tw.net;

import java.io.Serializable;

/**
 * @description 所有数据类基类(feed - 供给 ， 流入)
 */
public class Feed<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int status; // 200 成功
    protected String message; // 消息
    protected T data; //各种数据类型 真正需要解释的数据结构

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Feed{"
                + "status='" + status + '\''
                + ", msg='" + message + '\''
                + ", data=" + data
                + '}';
    }
}
