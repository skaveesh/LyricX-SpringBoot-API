package com.lyricxinc.lyricx.service.firebase;

/**
 * The type Media suggest factory.
 */
public class MediaSuggestFactory {

    /**
     * Get media suggestion media suggest operation.
     *
     * @param media the media
     * @return the media suggest operation
     */
    public MediaSuggestOperation getMediaSuggestion(Media media){

        if(media == Media.ALBUM){
            return new AlbumSuggestOperation();
        }

        return null;
    }

    /**
     * The enum Media.
     */
    public enum Media{
        /**
         * Album media.
         */
        ALBUM
    }
}
