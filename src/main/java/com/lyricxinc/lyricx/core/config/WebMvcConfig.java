package com.lyricxinc.lyricx.core.config;

import com.lyricxinc.lyricx.core.converter.AlbumCreateUpdateRequestToAlbumConverter;
import com.lyricxinc.lyricx.core.converter.ArtistCreateUpdateRequestToArtistConverter;
import com.lyricxinc.lyricx.core.converter.SongCreateUpdateRequestToSongConverter;
import com.lyricxinc.lyricx.core.interceptor.AdminInterceptor;
import com.lyricxinc.lyricx.core.interceptor.ChanterInterceptor;
import com.lyricxinc.lyricx.core.interceptor.ContributorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
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

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new SongCreateUpdateRequestToSongConverter());
        registry.addConverter(new AlbumCreateUpdateRequestToAlbumConverter());
        registry.addConverter(new ArtistCreateUpdateRequestToArtistConverter());
    }

}
