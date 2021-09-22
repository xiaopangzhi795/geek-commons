/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.coder;

import com.geek45.commons.coder.code.*;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类
 * @ClassName: CoderUtil
 * @Decription:
 * @Author: qian
 * qian create CoderUtil.java of 2021/9/22 7:20 下午
 */
public class CoderUtil {

    /**
     * 使用公钥对字符串进行加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String rsaEncodeByPubKey(String data, String publicKey) {
        byte[] key = Base64.decodeBase64(publicKey);
        try {
            byte[] encode = RSACoder.encryptByPublicKey(data.getBytes(), key);
            return Base64.encodeBase64String(encode);
        } catch (Exception e) {
            e.printStackTrace();
            return "加密失败:" + e.getLocalizedMessage();
        }
    }

    /**
     * 使用公钥对字符串进行加密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String rsaEncodeByPriKey(String data, String privateKey) {
        byte[] key = Base64.decodeBase64(privateKey);
        try {
            byte[] encode = RSACoder.encryptByPrivateKey(data.getBytes(), key);
            return Base64.encodeBase64String(encode);
        } catch (Exception e) {
            e.printStackTrace();
            return "加密失败:" + e.getLocalizedMessage();
        }
    }


    /**
     * 根据私钥解密
     *
     * @param enData
     * @param privateKey
     * @return
     */
    public static String rsaDecodeByPriKey(String enData, String privateKey) {
        byte[] key = Base64.decodeBase64(privateKey);
        byte[] encode = Base64.decodeBase64(enData);
        try {
            byte[] decode = RSACoder.decryptByPrivateKey(encode, key);
            return new String(decode);
        } catch (Exception e) {
            e.printStackTrace();
            return "解密失败:" + e.getLocalizedMessage();
        }

    }

    /**
     * 根据公钥解密
     *
     * @param enData
     * @param publicKey
     * @return
     */
    public static String rsaDecodeByPubKey(String enData, String publicKey) {
        byte[] key = Base64.decodeBase64(publicKey);
        byte[] encode = Base64.decodeBase64(enData);
        try {
            byte[] decode = RSACoder.decryptByPublicKey(encode, key);
            return new String(decode);
        } catch (Exception e) {
            e.printStackTrace();
            return "解密失败:" + e.getLocalizedMessage();
        }
    }

    /**
     * aes 加密
     * @param data
     * @param key
     * @param iv
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String aesEncode(String data, String key, String iv) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return AESCode.aesEncode(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * AES 解密
     * @param data
     * @param key
     * @param iv
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws DecoderException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String aesDecode(String data, String key, String iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, DecoderException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        return AESCode.aesDecode(data, key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * hmac 加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String hmac(String data, String key) throws Exception {
        return Base64.encodeBase64String(HmacCoder.encryptHMAC(data.getBytes(), key));
    }

    /**
     * md5 转16位
     * @param data
     * @return
     */
    public static String md5Convert16(String data) {
        return Md5Coder.md5(data).substring(8, 24);
    }

    /**
     * md5 加密
     * @param data
     * @return
     */
    public static String md5(String data) {
        return Md5Coder.md5(data);
    }

    /**
     * sha256 加密
     * @param data
     * @return
     */
    public static String sha256(String data) {
        return ShaCoder.sha256(data);
    }
}
