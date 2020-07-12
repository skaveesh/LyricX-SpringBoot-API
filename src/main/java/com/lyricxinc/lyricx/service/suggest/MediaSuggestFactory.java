package com.lyricxinc.lyricx.service.suggest;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;
import com.lyricxinc.lyricx.core.exception.LyricxBaseException;

/**
 * The type Media suggest factory.
 */
public class MediaSuggestFactory {

    /**
     * Get media suggestion media suggest operation.
     *
     * @param mediaType the media
     * @return the media suggest operation
     */
    public MediaSuggestOperation getMediaSuggestion(MediaType mediaType) {

        if (mediaType == MediaType.ALBUM)
        {
            return new AlbumSuggestOperation();
        }

        throw new LyricxBaseException(LYRICX_ERR_27.getErrorMessage(), LYRICX_ERR_27.name());
    }

    /**
     * The enum Media.
     */
    public enum MediaType {

        /**
         * Album media type.
         */
        ALBUM,
        /**
         * Artist media type.
         */
        ARTIST,
        /**
         * Song media type.
         */
        SONG,
        /**
         * All media types.
         */
        ALL
    }

}
