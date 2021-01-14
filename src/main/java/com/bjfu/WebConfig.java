package com.bjfu;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfig extends DelegatingWebMvcConfiguration {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/admin/product/img/**")
                .addResourceLocations("classpath:/static/admin/product/img/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/image/**")
                .addResourceLocations("classpath:/static/image/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/layui/**")
                .addResourceLocations("classpath:/static/layui/");
        registry.setOrder(-1);
    }

    @PostConstruct
    public void setProperties() {
        System.setProperty("druid.mysql.usePingMethod", "false");
    }
}