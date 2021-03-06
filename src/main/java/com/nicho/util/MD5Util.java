package com.nicho.util;

import org.springframework.util.DigestUtils;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/28 11:33
 */
 
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5DigestAsHex(src.getBytes());
    }

    public static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBpass(String inputPass ,String saltDB){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args){
        System.out.println(inputPassToFormPass("123456"));//d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(inputPassToDBpass("123456", "1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
    }
}
