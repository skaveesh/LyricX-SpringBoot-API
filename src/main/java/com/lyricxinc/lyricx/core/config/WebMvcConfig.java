package com.lyricxinc.lyricx.core.config;

import com.lyricxinc.lyricx.core.interceptor.AdminInterceptor;
import com.lyricxinc.lyricx.core.interceptor.ChanterInterceptor;
import com.lyricxinc.lyricx.core.interceptor.ContributorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new ContributorInterceptor()).addPathPatterns("/contributor/**").addPathPatterns("/artist/**").addPathPatterns("/album/**").addPathPatterns("/song/**").excludePathPatterns("/contributor/register");
        registry.addInterceptor(new ChanterInterceptor()).addPathPatterns("/chanter/**");

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

}
