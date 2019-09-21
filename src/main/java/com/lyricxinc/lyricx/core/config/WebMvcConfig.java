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

        //TODO:uncomment these to get the authorization working
//        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");
//        registry.addInterceptor(new ContributorInterceptor()).addPathPatterns("/contributor/**").excludePathPatterns("/contributor/register").addPathPatterns("/artist/**").addPathPatterns("/album/**");
//        registry.addInterceptor(new ChanterInterceptor()).addPathPatterns("/chanter/**");

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

}
