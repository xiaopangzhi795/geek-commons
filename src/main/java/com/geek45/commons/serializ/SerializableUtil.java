package com.geek45.commons.serializ;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Map;

@Slf4j
public class SerializableUtil {
    public static void main(String[] args) {
        Map map = (Map) deserialization("F:\\serialization\\data");
        System.err.println(JSONObject.toJSONString(map));
    }

    private static String defaultPath = "/data.json";

    /**
     * 序列化
     * @param path  序列化路径
     * @param data  序列化对象
     */
    public static void serialization(String path, Object data) {
        ObjectOutputStream oout = null;
        try {
            if (StringUtils.isBlank(path)) {
                path = defaultPath;
            }
            File file = new File(path);
            oout = new ObjectOutputStream(new FileOutputStream(file));
            oout.writeObject(data);
            log.info("序列化完成");
        } catch (Exception e) {
            log.error("序列化异常", e);
        }finally {
            try {
                if (oout != null) {
                    oout.close();
                }
            }catch (IOException e) {
                log.error("关闭异常", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 反序列化
     * @param path 反序列化路径
     * @return  反序列化对象
     */
    public static Object deserialization(String path) {
        ObjectInputStream oin = null;
        try {
            if (StringUtils.isBlank(path)) {
                path = defaultPath;
            }
            File file = new File(path);
            oin = new ObjectInputStream(new FileInputStream(file));
            Object object = oin.readObject();
            log.info("反序列化完成");
            return object;
        } catch (Exception e) {
            log.error("反序列化异常", e);
        }finally {
            try {
                if (oin != null) {
                    oin.close();
                }
            } catch (IOException e) {
                log.error("关闭异常", e);
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除序列化对象
     * @param path  序列化对象路径
     * @return  是否删除成功  true，删除成功，false，删除失败
     */
    public static boolean deleteSerializationObject(String path) {
        try {
            if (StringUtils.isBlank(path)) {
                path = defaultPath;
            }
            File file = new File(path);
            if (file.exists()) {
                log.info("删除成功");
                return file.delete();
            } else {
                log.info("文件不存在");
                return true;
            }
        } catch (Exception e) {
            log.error("删除异常", e);
            e.printStackTrace();
        }
        return false;
    }

}
