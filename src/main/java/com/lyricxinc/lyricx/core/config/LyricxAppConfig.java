package com.lyricxinc.lyricx.core.config;

import com.lyricxinc.lyricx.service.firebase.AlbumSuggestOperation;
import com.lyricxinc.lyricx.service.firebase.MediaSuggestFactory;
import com.lyricxinc.lyricx.service.firebase.MediaSuggestOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LyricxAppConfig {

    @Bean("com.lyricxinc.lyricx.service.firebase.MediaSuggestFactory")
    public MediaSuggestFactory getMediaSuggestFactory(){
        return new MediaSuggestFactory();
    }

    @Bean("com.lyricxinc.lyricx.service.firebase.AlbumSuggestOperation")
    public MediaSuggestOperation getAlbumSuggestOperation(){
        return new AlbumSuggestOperation();
    }
}
