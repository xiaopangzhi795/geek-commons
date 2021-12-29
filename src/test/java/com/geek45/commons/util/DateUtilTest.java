/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.util;

import com.geek45.commons.CommonsApplicationTests;
import com.geek45.commons.lang.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DateUtilTest
 * @Decription: 日期工具测试类
 * @Author: rubik
 * rubik create DateUtilTest.java of 2021/12/29 5:55 下午
 */
public class DateUtilTest extends CommonsApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(DateUtilTest.class);

    @Test
    public void testGetDateStr() {
        logger.info("default date is :{}", DateUtils.getDateStr());
        logger.info("format date is :{}....hello", DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
    }

}
