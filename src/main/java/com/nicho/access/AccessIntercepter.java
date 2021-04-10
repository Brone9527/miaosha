package com.nicho.access;

import com.alibaba.fastjson.JSON;
import com.nicho.bean.MiaoshaUser;
import com.nicho.redis.AccessKey;
import com.nicho.redis.RedisService;
import com.nicho.result.CodeMsg;
import com.nicho.result.Result;
import com.nicho.service.MiaoshaUserService;
import com.nicho.vo.MiaoshaVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/3/8 16:24
 */

@Service
public class AccessIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        if (handler instanceof HandlerMethod){
            MiaoshaUser user = getUser(request,response);
            UserContext.setUser(user);
            HandlerMethod hm = (HandlerMethod)handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.second();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if(needLogin){
                if(user == null){
                    render(response,CodeMsg.SESSION_ERROR);
                    return false;
                }
                key+="_" + user.getId();

            }else {
                //do nothing
            }
            AccessKey ak = AccessKey.withExpire(seconds);
            Integer count = redisService.get(ak, key,Integer.class );
            if(count == null){
                redisService.set(ak,key ,1 );
            }else if(count < maxCount){
                redisService.incr(ak,key);
            }else {
                render(response,CodeMsg.ACCESS_LIMIT_REACHED );
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, CodeMsg cm)throws Exception{
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(Result.error(cm));
        out.write(str.getBytes());
        out.flush();
        out.close();
    }

    private MiaoshaUser getUser(HttpServletRequest request,HttpServletResponse response){
        String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
        MiaoshaVo jsonObject = null;
        String bodyToken = null;
        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);

        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)&&StringUtils.isEmpty(bodyToken)){
            return null;
        }

        String tokenF = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        String token = StringUtils.isEmpty(bodyToken)?tokenF:bodyToken;
        return userService.getByToken(response,token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiNameToken) {
        Cookie[] cookies =request.getCookies();
        if(cookies==null||cookies.length<=0){
            return null;
        }
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals(cookiNameToken)){
                return cookie.getValue();
            }
        }
        return null;
    }

}


