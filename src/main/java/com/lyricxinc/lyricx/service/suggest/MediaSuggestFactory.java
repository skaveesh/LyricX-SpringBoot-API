package com.lyricxinc.lyricx.service.suggest;

import com.lyricxinc.lyricx.core.exception.MediaSuggestionException;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.LYRICX_ERR_27;

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
        } else if (mediaType == MediaType.ARTIST){
            return new ArtistSuggestOperation();
        }

        throw new MediaSuggestionException(LYRICX_ERR_27);
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
