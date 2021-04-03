package com.geek45.commons.exception;

import com.geek45.commons.lang.StringUtils;

/**
 * 断言
 * @author qian
 */
public class Asserts {

    public static void isBlank(String str, String errCode, String errMsg) {
        if (StringUtils.isBlank(str)) {
            throwException(errCode, errMsg);
        }
    }

    public static void isNull(Object obj, String errCode, String errMsg) {
        if (obj == null) {
            throwException(errCode, errMsg);
        }
    }

    public static void equalsTrue(boolean expression, String errCode, String errMsg) {
        if (expression) {
            throwException(errCode, errMsg);
        }
    }

    public static void throwException(String errCode, String errMsg) {
        throw new BizException(errCode, errMsg);
    }
}
