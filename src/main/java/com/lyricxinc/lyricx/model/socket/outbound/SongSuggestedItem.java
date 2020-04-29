package com.lyricxinc.lyricx.model.socket.outbound;

/**
 * The type Song suggest list.
 */
public class SongSuggestedItem implements SuggestedItem {

    private String surrogateKey;
    private String songName;

    /**
     * Instantiates a new Song suggest list.
     *
     * @param surrogateKey the surrogate key
     * @param songName     the song name
     */
    public SongSuggestedItem(String surrogateKey, String songName) {

        this.surrogateKey = surrogateKey;
        this.songName = songName;
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
     * Gets song name.
     *
     * @return the song name
     */
    public String getSongName() {

        return songName;
    }

    /**
     * Sets song name.
     *
     * @param songName the song name
     */
    public void setSongName(String songName) {

        this.songName = songName;
    }

}
