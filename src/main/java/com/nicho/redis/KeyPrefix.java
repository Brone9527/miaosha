package com.nicho.redis;
/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/25 14:10
 */
 
public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
