package com.lyricxinc.lyricx.model.socket.inbound;

/**
 * The type Album suggest.
 */
public class AlbumSuggest {

    private String albumName;

    /**
     * Instantiates a new Album suggest.
     *
     * @param albumName the album name
     */
    public AlbumSuggest(String albumName) {

        this.albumName = albumName;
    }

    public AlbumSuggest() {

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
