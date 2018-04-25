package com.jlhc.common.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 16:22 2018/1/19 0019
 */
public class MD5Utils {


    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str){
        //确定计算方法
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        BASE64Encoder base64en = new BASE64Encoder();
//        //加密后的字符串
//        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
//        return newstr;
        //jdk自带的MD5加密
        try {
            MessageDigest lDigest = MessageDigest.getInstance("MD5");
            lDigest.update(str.getBytes());
            BigInteger lHashInt = new BigInteger(1, lDigest.digest());
            return String.format("%1$032X", lHashInt);
        } catch (NoSuchAlgorithmException lException) {
            throw new RuntimeException(lException);
        }
    }

   public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String admin = MD5Utils.EncoderByMd5("tututu");
        System.out.println(admin);
    }
}
