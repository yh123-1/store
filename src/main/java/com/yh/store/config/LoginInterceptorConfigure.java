package com.yh.store.config;

import com.yh.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 完成处理器拦截器的注册
 */
@Configuration
public class LoginInterceptorConfigure implements WebMvcConfigurer {

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器的注册
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") //要拦截的url
                .excludePathPatterns("/css/**","/bootstrap3/**","/images/**","/js/**","/districts/**","/upload/**",
                        "/web/login.html","/web/register.html","/web/index.html","/web/product.html",
                        "/users/login","/users/reg","/products/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/idea_java_projects/store/src/main/resources/static/upload/");
    }
}
