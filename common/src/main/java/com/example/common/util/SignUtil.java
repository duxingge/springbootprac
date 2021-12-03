package com.example.common.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Author wangjiaxing
 * @Date 2021/10/6
 */
public class SignUtil {

    static String buildSign(String secretKey, String rawData) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(rawData.getBytes("UTF-8")));
    }


    public static void main(String[] args) {

        try {
            System.out.println("result: "+ buildSign("7095da5445f04ac93", "1633497975647"));

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
