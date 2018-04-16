package com.xianfu.common.exception;

import com.xianfu.common.enumeration.BaseCode;

/**
 * @author Created by xianfuWang
 * @version 2018/4/15.
 */
public class BaseException extends RuntimeException {

    private BaseCode errorCode;

    public BaseException(BaseCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public BaseException(BaseCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
