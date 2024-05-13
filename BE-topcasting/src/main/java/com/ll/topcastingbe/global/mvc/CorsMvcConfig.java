package com.ll.topcastingbe.global.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Value("${custom.site.front_url}")
    private String frontUrl;
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .exposedHeaders("Set-Cookie")
                .allowedOrigins(frontUrl);
    }
}
