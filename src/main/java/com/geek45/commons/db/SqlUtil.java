/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.db;


import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: SqlUtil
 * @Decription:
 * @Author: rubik
 *  rubik create SqlUtil.java of 2021/12/28 10:12 下午
 */
public class SqlUtil {

    public static final String generatorLikeByCondition(String condition) {
        if (StringUtils.isBlank(condition)) {
            return condition;
        }
        //TODO 去掉原有的%符号
        return StringUtils.join("%", condition, "%");
    }
}
