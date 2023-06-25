package com.lyricxinc.lyricx.model.socket.outbound;

/**
 * The type Album suggest list.
 */
public class AlbumSuggestedItem implements SuggestedItem {

    private String surrogateKey;
    private String albumName;

    /**
     * Instantiates a new Album suggest list.
     *
     * @param surrogateKey the surrogate key
     * @param albumName    the album name
     */
    public AlbumSuggestedItem(String surrogateKey, String albumName) {

        this.surrogateKey = surrogateKey;
        this.albumName = albumName;
    }

    public AlbumSuggestedItem() {

    }

    /**
     * Gets surrogate key.
     *
     * @return the surrogate key
     */
    public String getSurrogateKey() {

        return surrogateKey;
    }

    /**
     * Sets surrogate key.
     *
     * @param surrogateKey the surrogate key
     */
    public void setSurrogateKey(String surrogateKey) {

        this.surrogateKey = surrogateKey;
    }

    /**
     * Gets album name.
     *
     * @return the album name
     */
    public String getAlbumName() {

        return albumName;
    }

    /**
     * Sets album name.
     *
     * @param albumName the album name
     */
    public void setAlbumName(String albumName) {

        this.albumName = albumName;
    }

}
