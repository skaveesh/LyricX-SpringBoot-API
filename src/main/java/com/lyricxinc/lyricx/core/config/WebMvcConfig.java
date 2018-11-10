package com.lyricxinc.lyricx.core.config;

import com.lyricxinc.lyricx.core.ContributorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContributorInterceptor()).addPathPatterns("/contributor/**").excludePathPatterns("/contributor/login");

    }
}
