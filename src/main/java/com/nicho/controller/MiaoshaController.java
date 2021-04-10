package com.nicho.controller;

import com.nicho.access.AccessLimit;
import com.nicho.bean.MiaoshaOrder;
import com.nicho.bean.MiaoshaUser;
import com.nicho.bean.OrderInfo;
import com.nicho.result.CodeMsg;
import com.nicho.result.Result;
import com.nicho.service.GoodsService;
import com.nicho.service.MiaoshaService;
import com.nicho.service.OrderService;
import com.nicho.vo.GoodsVo;
import com.nicho.vo.MiaoshaVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/2 19:50
 */


@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;
    /**
     * QPS:270
     * 1000 *10
     * QPS:
     */
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @AccessLimit(second = 5,maxCount = 5,needLogin = true)
    @RequestMapping(value = "/{path}/do_miaosha",method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> miaosha(Model model, MiaoshaUser user, @RequestBody MiaoshaVo miaoshaVo, @PathVariable("path") String path){
       log.info(String.valueOf(user));
       model.addAttribute("user",user);
       Long goodsId = Long.parseLong(miaoshaVo.getGoodsId());
       log.info("body参数--："+String.valueOf(goodsId));
       if(user == null){
           return Result.error(CodeMsg.SESSION_ERROR);
       }
        //验证path
        boolean check = miaoshaService.checkPath(user, goodsId, path);
        if(!check){
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
       //判断库存
       GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
       log.info(String.valueOf(goods));
       int stock =goods.getStockCount();
       if(stock<=0){
           model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
           return Result.error(CodeMsg.MIAO_SHA_OVER);
       }

        //判断是否已经秒杀到
        MiaoshaOrder order =orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
       if(order !=null){
           //model.addAttribute("errmsg",CodeMsg.REPEATE_MIAOSHA.getMsg());
           return Result.error(CodeMsg.REPEATE_MIAOSHA);
       }
        //减库存   下订单   写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
       //model.addAttribute("orderInfo",orderInfo);
       //model.addAttribute("goods",goods);
        return Result.success(orderInfo);
    }




































    /**
     * orderId：成功
     * -1：秒杀失败
     * 0： 排队中
     * */
    @RequestMapping(value="/result", method=RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(Model model,MiaoshaUser user,
                                      @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result  =miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(result);
    }

    @AccessLimit(second=5, maxCount=5, needLogin=true)
    @RequestMapping(value="/path", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaPath(HttpServletRequest request, MiaoshaUser user,
                                         @RequestParam("goodsId")long goodsId,
                                         @RequestParam(value="verifyCode", defaultValue="0")int verifyCode
    ) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
        if(!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        String path  =miaoshaService.createMiaoshaPath(user, goodsId);
        return Result.success(path);
    }


    @RequestMapping(value="/verifyCode", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaVerifyCod(HttpServletResponse response, MiaoshaUser user,
                                              @RequestParam("goodsId")long goodsId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        try {
            BufferedImage image  = miaoshaService.createVerifyCode(user, goodsId);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return null;
        }catch(Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.MIAOSHA_FAIL);
        }
    }
}
