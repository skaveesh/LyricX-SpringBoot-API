package com.lyricxinc.lyricx.core.config;

import com.lyricxinc.lyricx.core.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Filter registration config.
 */
@Configuration
public class FilterRegistrationConfig {

    /**
     * Logging filter filter registration bean.
     *
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean<AuthFilter> loggingFilter() {

        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

}
