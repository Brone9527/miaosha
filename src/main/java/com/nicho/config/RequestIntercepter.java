//package com.nicho.config;
//
//import com.nicho.filter.HttpRequestWrapper;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author qiwl
// * @version 1.0
// * @description
// * @date 2021/2/26 15:40
// */
//
//@Component
//public class RequestIntercepter  implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("uri="+request.getRequestURI());
//        try {
//            HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
//            String body = requestWrapper.getBody();
//            System.out.println("拦截器获取到body内容:"+body);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//}
