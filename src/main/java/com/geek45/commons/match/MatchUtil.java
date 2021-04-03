package com.geek45.commons.match;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchUtil {

    /**
     * 根据正则表达式比对是否符合要求
     * @param content   要比对的文本
     * @param patterns  比对使用的正则表达式
     * @return
     */
    public static boolean matchStrByPatterns(String content, String... patterns) {
        if (StringUtils.isNotBlank(content)) {
            boolean isMatch = false;
            reTry:
            for (String pattern : patterns
            ) {
                isMatch = Pattern.matches(pattern, content);
                if (isMatch) {
                    break reTry;
                }
            }
            return isMatch;
        }
        return false;
    }

    /**
     * 匹配某个字符串在文本中出现的次数
     * @param content       要查询的文本
     * @param patterns      要比对的字符串或者正则表达式
     * @return
     */
    public static int matchStrNum(String content, String patterns) {
        if (StringUtils.isNotBlank(content) && StringUtils.isNotBlank(patterns)) {
            try {
                Pattern c = Pattern.compile(patterns);
                Matcher mc = c.matcher(content);
                int i = 0;
                while (mc.find()) {
                    i++;
                }
                return i;
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.err.println(matchStrNum("我发的发的萨芬的扫尾发接二连三减肥我去我就福娃片可分为费劲儿啥；老师我", "[我]"));
    }
}
