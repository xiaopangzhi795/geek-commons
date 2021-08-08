/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.rpc;

import com.geek45.commons.lang.UUIDUtil;

/**
 * @ClassName: RpcContext
 * @Decription:
 * @Author: qian
 * qian create RpcContext.java of 2021/8/7 10:15 上午
 */
public class RpcContext {
    private static ThreadLocal<String> context = new ThreadLocal<>();

    public static void generatorRpc() {
        String uid = UUIDUtil.uuid();
        context.set(uid);
    }

    public static String get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }
}
