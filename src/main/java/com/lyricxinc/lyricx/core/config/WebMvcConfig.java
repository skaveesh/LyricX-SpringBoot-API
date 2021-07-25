package com.lyricxinc.lyricx.core.config;

import com.lyricxinc.lyricx.core.converter.todto.AlbumToAlbumDTOConverter;
import com.lyricxinc.lyricx.core.converter.todto.ArtistToArtistDTOConverter;
import com.lyricxinc.lyricx.core.converter.todto.ContributorToContributorDTOConverter;
import com.lyricxinc.lyricx.core.converter.todto.SongToSongDTOConverter;
import com.lyricxinc.lyricx.core.converter.tomodel.*;
import com.lyricxinc.lyricx.core.interceptor.AdminInterceptor;
import com.lyricxinc.lyricx.core.interceptor.ChanterInterceptor;
import com.lyricxinc.lyricx.core.interceptor.ContributorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

        // to model
        registry.addConverter(new SongDTOToSongConverter());
        registry.addConverter(new AlbumDTOToAlbumConverter());
        registry.addConverter(new ArtistDTOToArtistConverter());
        registry.addConverter(new GenreDTOToGenreConverter());
        registry.addConverter(new LanguageDTOToLanguageConverter());

        // to dto
        registry.addConverter(new SongToSongDTOConverter());
        registry.addConverter(new AlbumToAlbumDTOConverter());
        registry.addConverter(new ArtistToArtistDTOConverter());
        registry.addConverter(new ContributorToContributorDTOConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
                .allowedOrigins("http://localhost:4200");
    }

}
