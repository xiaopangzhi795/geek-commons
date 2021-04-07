package com.geek45.commons.request;

import com.geek45.commons.exception.ExceptionHandler;
import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author qian
 */
public class RequestUtil {

    public static UASparser uaSparser;

    static {
        try {
            uaSparser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (Exception e) {
            throw new RuntimeException("初始化失败");
        }
    }

    @ExceptionHandler
    public static UserAgentInfo userAgentInfo(HttpServletRequest request) throws IOException {
        String userAgent = userAgent(request);
        return uaSparser.parse(userAgent);
    }

    /**
     * 获取ip
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader(RequestConstant.FORWARDED_IP);
        if (StringUtils.isNotBlank(ip) && !StringUtils.equals(RequestConstant.UN_KNOWN_STR, ip)) {
            int index = ip.indexOf(RequestConstant.SPLIT_STR);
            if (index != -1) {
                return ip.substring(0, index);
            }
            return ip;
        }
        ip = request.getHeader(RequestConstant.REAL_IP);
        if (StringUtils.isNotBlank(ip) && StringUtils.equals(ip, RequestConstant.UN_KNOWN_STR)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取userAgent
     * @param request
     * @return
     */
    public static String userAgent(HttpServletRequest request) {
        String userAgent = request.getHeader(RequestConstant.USER_AGENT);
        if (StringUtils.isBlank(userAgent)) {
            userAgent = request.getHeader(RequestConstant.user_agent);
        }
        return userAgent;
    }



}
