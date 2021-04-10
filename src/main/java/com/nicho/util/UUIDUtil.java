package com.nicho.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/31 17:58
 */
 
public class UUIDUtil {

    @Autowired
    DBUtil dbUtil;
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
