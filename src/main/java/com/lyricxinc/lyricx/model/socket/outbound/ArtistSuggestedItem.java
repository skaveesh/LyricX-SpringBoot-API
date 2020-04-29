package com.lyricxinc.lyricx.model.socket.outbound;

/**
 * The type Artist suggest list.
 */
public class ArtistSuggestedItem implements SuggestedItem {

    private String surrogateKey;
    private String artistName;

    /**
     * Instantiates a new Artist suggest list.
     *
     * @param surrogateKey the surrogate key
     * @param artistName   the artist name
     */
    public ArtistSuggestedItem(String surrogateKey, String artistName) {

        this.surrogateKey = surrogateKey;
        this.artistName = artistName;
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
