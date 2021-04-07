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
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
