package com.lyricxinc.lyricx.model.socket.inbound;

/**
 * The type All media suggest.
 */
public class AllMediaSuggest {

    private String mediaName;

    /**
     * Instantiates a new All media suggest.
     *
     * @param mediaName the media name
     */
    public AllMediaSuggest(String mediaName) {

        this.mediaName = mediaName;
    }

    /**
     * Instantiates a new All media suggest.
     */
    public AllMediaSuggest() {

    }

    /**
     * Gets media name.
     *
     * @return the media name
     */
    public String getMediaName() {

        return mediaName;
    }

    /**
     * Sets media name.
     *
     * @param mediaName the media name
     */
    public void setMediaName(String mediaName) {

        this.mediaName = mediaName;
    }

}
