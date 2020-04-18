package com.lyricxinc.lyricx.model.socket.model.outbound;

import java.util.List;
import java.util.Map;

/**
 * The type Album suggest list.
 */
public class AlbumSuggestList {

    private Map<String, String> albumMap;

    /**
     * Instantiates a new Album suggest list.
     *
     * @param albumMap the album map
     */
    public AlbumSuggestList(Map<String, String> albumMap) {

        this.albumMap = albumMap;
    }

    /**
     * Gets album map.
     *
     * @return the album map
     */
    public Map<String, String> getAlbumMap() {

        return albumMap;
    }

    /**
     * Sets album map.
     *
     * @param albumMap the album map
     */
    public void setAlbumMap(Map<String, String> albumMap) {

        this.albumMap = albumMap;
    }

}
