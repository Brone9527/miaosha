package com.nicho.vo;

import com.nicho.bean.OrderInfo;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/23 20:53
 */
 
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
