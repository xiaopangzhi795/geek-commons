package com.geek45.commons.request;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qian
 */
public class CookieUtils {

    /**
     * 按名称获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || StringUtils.isEmpty(name)) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    /**
     * 添加cookie
     *
     * @param name
     * @param value
     * @param path
     * @param request
     * @param response
     */
    public static void addCookie(String name, String value, String path, HttpServletRequest request,
                                 HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        if (path != null) {
            cookie.setPath(path);
        }
        if ("https".equals(request.getScheme())) {
            cookie.setSecure(true);
        }
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
