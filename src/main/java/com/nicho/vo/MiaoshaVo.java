package com.nicho.vo;

import java.io.Serializable;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/25 18:23
 */
 
public class MiaoshaVo implements Serializable {
    private  String goodsId;
    private  String token;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "MiaoshaVo{" +
                "goodsId='" + goodsId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
