package com.nicho.util;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/30 9:46
 */
 
public class ValidatorUtil {
    public static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();

    }

//    public static void main(String[] args) {
//        System.out.println(isMobile("15071305619"));
//        System.out.println(isMobile("22342234536"));
//    }
}
