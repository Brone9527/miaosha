package com.nicho.controller;

import com.nicho.rabbitmq.MQSender;
import com.nicho.redis.RedisService;
import com.nicho.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/3/1 15:54
 */

@Controller
@RequestMapping("/demo")
public class SimpleController {

    @Autowired
    MQSender sender;
    @Autowired
    RedisService redisService;

    @RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic(){
        sender.sendTopic("hello,nicho");
        return Result.success("Hello world!");
    }
}
