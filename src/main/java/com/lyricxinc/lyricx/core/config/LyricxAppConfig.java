package com.lyricxinc.lyricx.core.config;

import com.lyricxinc.lyricx.service.suggest.AlbumSuggestOperation;
import com.lyricxinc.lyricx.service.suggest.MediaSuggestFactory;
import com.lyricxinc.lyricx.service.suggest.MediaSuggestOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Lyricx app config.
 */
@Configuration
public class LyricxAppConfig {

    /**
     * Gets media suggest factory.
     *
     * @return the media suggest factory
     */
    @Bean("com.lyricxinc.lyricx.service.firebase.MediaSuggestFactory")
    public MediaSuggestFactory getMediaSuggestFactory() {

        return new MediaSuggestFactory();
    }

    /**
     * Gets album suggest operation.
     *
     * @return the album suggest operation
     */
    @Bean("com.lyricxinc.lyricx.service.firebase.AlbumSuggestOperation")
    public MediaSuggestOperation getAlbumSuggestOperation() {

        return new AlbumSuggestOperation();
    }

}
