package com.nicho.redis;
/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/23 20:57
 */
 
public class OrderKey extends BasePrefix {
    public OrderKey(String prefix) {
        super(prefix);
    }

  public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
