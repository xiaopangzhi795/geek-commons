/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.lang;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @ClassName: DateUtils
 * @Decription: 日期工具类
 * @Author: rubik
 *  rubik create DateUtils.java of 2021/12/29 5:49 下午
 */
public class DateUtils {


    /**
     *  获取当前时间字符串
     *  格式为 YYYY-MM-dd HH:mm:ss
     * @return
     */
    public static final String getDateStr() {
        return getDateStr("YYYY-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前时间字符串
     * @param pattern 格式化
     * @return
     */
    public static final String getDateStr(String pattern) {
        LocalDateTime dateTime = LocalDateTime.now();
        String dateStr = dateTime.toString(DateTimeFormat.forPattern(pattern));
        return dateStr;
    }
}
