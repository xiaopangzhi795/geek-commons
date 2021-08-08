package com.geek45.commons.exception;

import com.geek45.commons.lang.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

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

    public static void isEmpty(Collection<?> coll, String errCode, String errMsg) {
        if (CollectionUtils.isEmpty(coll)) {
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

    public static void equalsFalse(boolean expression, String errCode, String errMsg) {
        if (!expression) {
            throwException(errCode, errMsg);
        }
    }

    public static void throwValidatorException(String code, String message) {
        throw new GeekValidatorException(code, message);
    }

    public static void throwBizException(String code, String message) {
        throw new GeekBizException(code, message);
    }

    public static void throwCommonException(String code, String message){
        throw new GeekCommonException(code, message);
    }

    public static void throwException(String code, String message) throws GeekException {
        throw new GeekException(code, message);
    }

}
