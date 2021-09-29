package com.example.common.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @Author wangjiaxing
 * @Date 2021/6/21
 */
public class ConvertUtil {

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * HmacSHA256加密
     *
     * @param data 明文字符串
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA256ToString(String data, String key) {
        return encryptHmacSHA256ToString(data.getBytes(), key.getBytes());
    }

    /**
     * HmacSHA256加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA256ToString(byte[] data, byte[] key) {
        return bytes2HexString(encryptHmacSHA256(data, key));
    }

    /**
     * byteArr转hexString
     * <p>例如：</p>
     * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
     *
     * @param bytes 字节数组
     * @return 16进制大写字符串
     */
    public static String bytes2HexString(byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    /**
     * HmacSHA256加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptHmacSHA256(byte[] data, byte[] key) {
        return hmacTemplate(data, key, "HmacSHA256");
    }
    /**
     * Hmac加密模板
     *
     * @param data      数据
     * @param key       秘钥
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    private static byte[] hmacTemplate(byte[] data, byte[] key, String algorithm) {
        if (data == null || data.length == 0 || key == null || key.length == 0) return null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String frontEndHash(String clearString) {
        //TODO 待修正
        String key = encryptHmacSHA256ToString(clearString,"KreditPintar");
        String password = encryptHmacSHA256ToString(clearString,key);

        return password;
    }



    public static List<String> getRepeatStrings(int len,int sameSize) {
        List<String> ss = new ArrayList<>();
        double end = Math.pow(10, len);
        for (int i = 0; i < end; i++) {
            String s1 = String.valueOf(i);
            String s2 = sixString(s1);
            if(Pattern.matches("^.*(.)\\1{"+(sameSize-1)+"}.*$",s2)) {
                System.out.println(frontEndHash(s2));
                ss.add(s2);
            }
        }
        System.out.println("end:" +end);
        System.out.println("size: "+ ss.size());
        return ss;
    }





    private static String sixString(String s1) {
        char[] chars = s1.toCharArray();
        int len = 6 - chars.length;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }
        sb.append(s1);
        return sb.toString();
    }
    private static Set<String> getSerialStrings(int size) {
        String template1 = "0123456789";
        String template2 = "9876543210";
        Set<String> strs = new HashSet<>();
        for (int i = 0; i < 11 - size ; i++) {
            String sixStr = template1.substring(i, size+ i);
            String sixStr2 = template2.substring(i, size+ i);
            strs.add(sixStr);
            System.out.println(sixStr);
            System.out.println(sixStr2);
            strs.add(sixStr2);
        }

        return strs;
    }

    public static void main(String[] args) {
        System.out.println(frontEndHash("a12391"));
//        System.out.println(JsonUtil.toString(getSerialStrings(6)));
//        getSerialStrings(6);
    }

}
