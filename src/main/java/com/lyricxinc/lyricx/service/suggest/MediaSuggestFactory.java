package com.lyricxinc.lyricx.service.suggest;

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
    public MediaSuggestOperation getMediaSuggestion(MediaType mediaType){

        if(mediaType == MediaType.ALBUM){
            return new AlbumSuggestOperation();
        }

        return null;
    }

    /**
     * The enum Media.
     */
    public enum MediaType {
        /**
         * Album media.
         */
        ALBUM
    }
}
