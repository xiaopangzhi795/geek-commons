package com.geek45.commons.bean;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

/**
 * javaBean的操作工具类
 */
public class BeanUtil {

    public static void shallowCopy(Object src, Object des) {
        BeanUtils.copyProperties(src, des);
    }

}
