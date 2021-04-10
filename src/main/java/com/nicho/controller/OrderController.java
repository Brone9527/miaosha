package com.nicho.controller;

import com.nicho.bean.MiaoshaUser;
import com.nicho.bean.OrderInfo;
import com.nicho.result.CodeMsg;
import com.nicho.result.Result;
import com.nicho.service.GoodsService;
import com.nicho.service.OrderService;
import com.nicho.vo.GoodsVo;
import com.nicho.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/23 20:50
 */

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user, @RequestParam("orderId") long orderId){
        if(user ==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}
