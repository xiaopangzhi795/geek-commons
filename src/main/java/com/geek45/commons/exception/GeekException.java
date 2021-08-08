/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.exception;

import lombok.Data;

/**
 * @ClassName: GeekException
 * @Decription:
 * @Author: qian
 * qian create GeekException.java of 2021/7/26 8:46 下午
 */
@Data
public class GeekException extends RuntimeException {
    /**
     * 异常Code值
     */
    private String code;

    public GeekException(String message) {
        super(message);
    }

    public GeekException(String code, String message) {
        super(message);
        this.setCode(code);
    }
}
