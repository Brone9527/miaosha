package com.nicho.access;

import com.nicho.bean.MiaoshaUser;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/3/8 18:26
 */
 
public class UserContext {
    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();
    public static void setUser(MiaoshaUser user){
        userHolder.set(user);
    }
    public static MiaoshaUser getUser(){
        return userHolder.get();
    }
}
