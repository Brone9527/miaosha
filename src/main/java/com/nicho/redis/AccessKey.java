package com.nicho.redis;
/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/3/8 19:01
 */
 
public class AccessKey extends BasePrefix {

    public AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds){
        return new AccessKey(expireSeconds, "acess");
    }
}
