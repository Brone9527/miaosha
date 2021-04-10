package com.nicho.config;

import com.nicho.access.UserContext;
import com.nicho.bean.MiaoshaUser;
import com.nicho.redis.RedisService;
import com.nicho.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2021/2/1 9:52
 */

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private static Logger log = LoggerFactory.getLogger(UserArgumentResolver.class);
    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;


    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> calzz = methodParameter.getParameterType();
        return calzz == MiaoshaUser.class;
    }



    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
       /* HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        log.info(request.toString());
        HttpServletResponse response =nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
        log.info("url后的token---："+paramToken);
        MiaoshaVo jsonObject = null;
        String bodyToken = null;
        try {
            HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
            String body = requestWrapper.getBody();
            log.info("转化JSON之前的body--："+body);
//            String bodyUnSerial = StringEscapeUtils.unescapeJava(body);
//            jsonObject= JSONObject.parseObject(bodyUnSerial);
            jsonObject = redisService.stringToBean(body, MiaoshaVo.class);
            System.out.println("拦截器获取到body内容:"+jsonObject);
            bodyToken = jsonObject.getToken();
//            if(!StringUtils.isEmpty(body)&&jsonObject.getToken("token")){
//                bodyToken = jsonObject.getString("token");
//            }else {
//                bodyToken = null;
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("body中的token---："+bodyToken);
        String cookieToken = getCookieValue(request,MiaoshaUserService.COOKI_NAME_TOKEN);
        log.info("cookie中的token---:"+cookieToken);
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)&&StringUtils.isEmpty(bodyToken)){
            return null;
        }

        String tokenF = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        String token = StringUtils.isEmpty(bodyToken)?tokenF:bodyToken;
        log.info("请求秒杀接口请求头或者报文中的token---："+token);
        return userService.getByToken(response,token);*/

        return UserContext.getUser();
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
