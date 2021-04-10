package com.nicho.controller;

import com.nicho.result.Result;
import com.nicho.service.MiaoshaUserService;
import com.nicho.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/1/28 18:28
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    MiaoshaUserService userService;
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        String token = userService.login(response,loginVo);
        return Result.success(token);

    }
}
