package com.lyricxinc.lyricx.model.socket.model.inbound;

/**
 * The type Artist suggest.
 */
public class ArtistSuggest {

    private String artistName;

    /**
     * Instantiates a new Artist suggest.
     *
     * @param artistName the artist name
     */
    public ArtistSuggest(String artistName) {

        this.artistName = artistName;
    }

    /**
     * Gets artist name.
     *
     * @return the artist name
     */
    public String getArtistName() {

        return artistName;
    }

    /**
     * Sets artist name.
     *
     * @param artistName the artist name
     */
    public void setArtistName(String artistName) {

        this.artistName = artistName;
    }

}
