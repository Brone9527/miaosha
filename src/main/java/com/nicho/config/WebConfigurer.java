//package com.nicho.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author qiwl
// * @version 1.0
// * @description
// * @date 2021/2/26 15:43
// */
//
//
//public class WebConfigurer implements WebMvcConfigurer {
//    @Autowired
//    private RequestIntercepter requestIntercepter;
//
//    /**
//     * 这个方法是用来配置静态资源的，比如html，js，css，等等
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    }
//
//
//    /**
//     * 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // addPathPatterns("/**") 表示拦截所有的请求，
//        // addPathPatterns("/test/**") 表示拦截/test/ 下的所有路径请求，
//        // addPathPatterns("/test/*") 表示拦截/test/abc，拦截/test/aaa , 不拦截 /test/abc/def
//        // addPathPatterns("/test/**").excludePathPatterns("/test/login", "/test/register") 表示拦截/test/ 下的所有路径请求，但不拦截 /test/login 和 /test/register
//        registry.addInterceptor(requestIntercepter).addPathPatterns("/**").excludePathPatterns("/static/**","/resources/**","/js/**","/layer/**","/jquery-validation/**","/jquery-bootstrap/**","/img/**");
//    }
//
//}
