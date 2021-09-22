/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.coder.code;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: AESCode
 * @Decription:
 * @Author: qian
 * qian create AESCode.java of 2021/9/18 7:07 下午
 */
public class AESCode {

    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String HEX = "0123456789ABCDEF";
    private static final String MODE = "AES";

    public static String aesEncode(byte[] data, byte[] key, byte[] initVector) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec skeySpec = new SecretKeySpec(key, MODE);
        Cipher cipher = Cipher.getInstance(AES_CBC);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] result = cipher.doFinal(data);
        return Base64.encodeBase64String(result);
    }

    public static String aesDecode(String data, byte[] key, byte[] initVector) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, DecoderException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, MODE);
        // 解密
        Cipher cipher = Cipher.getInstance(AES_CBC);
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
        byte[] b = Base64.decodeBase64(data);
        byte[] result = cipher.doFinal(b);
        return new String(result);
    }

    /**
     * 将byte转换为16进制字符串
     * @param src
     * @return
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) HEX.indexOf(c);
    }

}
