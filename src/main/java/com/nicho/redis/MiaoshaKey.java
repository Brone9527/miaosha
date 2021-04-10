package com.nicho.redis;
/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/26 18:06
 */
 
public class MiaoshaKey extends BasePrefix {


    public MiaoshaKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey(0, "go");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey(60,"mp" );
    public static MiaoshaKey getMiaoshaVerifyCode  = new MiaoshaKey(300,"vc" );
}
