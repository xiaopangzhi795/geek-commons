package com.geek45.commons.request;

/**
 * 常用常量
 * @author qian
 */
public interface RequestConstant {

    /**
     * 路由
     */
    String FORWARDED_IP = "X-Forwarded-For";
    String REAL_IP = "X-Real-IP";
    String UN_KNOWN_STR = "unKnown";
    String SPLIT_STR = ",";

    String user_agent = "user-agent";
    String USER_AGENT = "User-Agent";

}
