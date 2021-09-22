/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.coder.code;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName: ShaCoder
 * @Decription:
 * @Author: qian
 * qian create ShaCoder.java of 2021/9/16 11:03 下午
 */
public class ShaCoder {

    private static final String KEY = "YTDXCMQ5b7sphr";

    public static String generatorShaStr() {
        return DigestUtils.sha512_256Hex(KEY.getBytes());
    }

    public static String sha256(String data) {
        return DigestUtils.sha512_256Hex(data + generatorShaStr());
    }

}
