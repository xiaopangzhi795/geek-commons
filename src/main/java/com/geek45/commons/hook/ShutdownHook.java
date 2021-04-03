package com.geek45.commons.hook;

import com.geek45.commons.serializ.SerializableUtil;
import com.geek45.commons.ssh.SSHUtil;

import java.util.Map;

/**
 * 系统关闭前执行的任务
 */
public class ShutdownHook {
    public static void shutdownHook(SSHUtil sshUtil) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Map<String, Object> objects = sshUtil.getData();
            for (String key : objects.keySet()) {
                SerializableUtil.serialization("F:\\serialization\\" + key, objects.get(key));
            }
        }));
    }

    public static void addShutdownHoos(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }
}
