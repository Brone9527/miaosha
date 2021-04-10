//package com.nicho.filter;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//
///**
// * @author qiwl
// * @version 1.0
// * @description
// * @date 2021/2/25 19:54
// */
//
//@Component
//@WebFilter(urlPatterns = "/*",filterName = "HttpServletFilter")
//public class HttpServletFilter implements Filter {
//
//    private static Logger log = LoggerFactory.getLogger(HttpServletFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        ServletRequest requestWrapper = null;
//        log.info("进入拦截器--："+servletRequest);
//        if(servletRequest instanceof HttpServletRequest) {
//            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//
//            // 遇到post方法才对request进行包装
//            String methodType = httpRequest.getMethod();
//            // 上传文件时同样不进行包装
//            String servletPath = httpRequest.getRequestURI().toString();
//            if ("POST".equals(methodType) && !servletPath.contains("/material/upload")) {
//                requestWrapper = new HttpRequestWrapper((HttpServletRequest) servletRequest);
//            }
////            requestWrapper = new HttpRequestWrapper((HttpServletRequest) servletRequest);
//        }
//        if(requestWrapper == null) {
//            System.out.println("servletRequest");
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            System.out.println("requestWrapper");
//            filterChain.doFilter(requestWrapper, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
