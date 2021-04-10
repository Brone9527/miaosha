package com.nicho.redis;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/20 15:54
 */
 
public class GoodsKey extends BasePrefix {

    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
}
