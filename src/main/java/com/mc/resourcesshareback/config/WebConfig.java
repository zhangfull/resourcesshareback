package com.mc.resourcesshareback.config;

import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // @SuppressWarnings("null")
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     // 将请求的 "/avatars/**" 映射到 "classpath:/static/avatars/" 文件夹
    //     registry.addResourceHandler("/avatars/**")
    //             .addResourceLocations("classpath:/static/avatars/");
    //     WebMvcConfigurer.super.addResourceHandlers(registry);
    // }
    // Add any additional configuration here if needed
    // For example, you can override methods to customize the behavior of the web
    // application
    // like adding interceptors, view resolvers, etc.

}
