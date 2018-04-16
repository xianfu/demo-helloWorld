package com.xianfu.common.enumeration;

/**
 * @author Created by xianfuWang
 * @version 2018/4/15.
 */
public enum BaseCode {

    SUCCESS(200, "success"),

    ACCESS_DENIED(403, "请求失败"),

    REQUEST_PARAM_IS_EMPTY(10000, "请求参数为空");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    BaseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
