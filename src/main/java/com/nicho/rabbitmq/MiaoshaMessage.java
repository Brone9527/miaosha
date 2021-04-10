package com.nicho.rabbitmq;

import com.nicho.bean.MiaoshaUser;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/3/6 10:59
 */
 
public class MiaoshaMessage {
    private MiaoshaUser user;
    private long goodsId;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
