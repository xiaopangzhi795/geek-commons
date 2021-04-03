package com.geek45.commons.exception;

/**
 * 基础异常类
 * @author qian
 */
public class BizException extends RuntimeException {

    private String errCode;
    private String errMsg;

    public BizException() {
        super();
    }

    public BizException(String errCode, String errMsg) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
