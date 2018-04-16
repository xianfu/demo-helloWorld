package com.xianfu.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Created by xianfuWang
 * @version 2018/4/16.
 */
public class WeChatUtils {

    public static boolean checkSignature(String signature, String token, String timestamp, String nonce) {
        List<String> list = Arrays.asList(token, timestamp, nonce);
        Collections.sort(list);
        String SHA1String = String.join("", list);
        SHA1String = SHA1(SHA1String);
        if (SHA1String.equals(signature)) {
            return true;
        }
        return false;
    }

    public static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");     //选择SHA-1，也可以选择MD5
            byte[] digest = md.digest(inStr.getBytes());       //返回的是byet[]，要转化为String存储比较方便
            outStr = bytetoString(digest);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }

    public static String bytetoString(byte[] digest) {
        String str = "";
        String tempStr = "";
        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            } else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }
}
