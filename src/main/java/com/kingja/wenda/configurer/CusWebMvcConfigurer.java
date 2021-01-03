package com.kingja.wenda.configurer;

import com.kingja.wenda.interceptor.UserInfoInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:TODO
 * Create Time:2020/12/26 0026 1:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Configuration
public class CusWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    UserInfoInterceptor userInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截http请求，排除静态资源的请求
        registry.addInterceptor(userInfoInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/icon/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/threepart/**");
    }

    /**
     * 开放静态资源的访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
