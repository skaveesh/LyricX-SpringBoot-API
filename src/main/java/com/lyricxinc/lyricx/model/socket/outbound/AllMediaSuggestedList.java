package com.lyricxinc.lyricx.model.socket.outbound;

import java.util.List;

/**
 * The type All media suggest list.
 */
public class AllMediaSuggestedList {

    private List<ArtistSuggestedItem> artistSuggestedItem;
    private List<AlbumSuggestedItem> albumSuggestedItem;
    private List<SongSuggestedItem> songSuggestedItem;

    /**
     * Instantiates a new All media suggest list.
     *
     * @param artistSuggestedItem the artist suggest list
     * @param albumSuggestedItem  the album suggest list
     * @param songSuggestedItem   the song suggest list
     */
    public AllMediaSuggestedList(List<ArtistSuggestedItem> artistSuggestedItem, List<AlbumSuggestedItem> albumSuggestedItem, List<SongSuggestedItem> songSuggestedItem) {

        this.artistSuggestedItem = artistSuggestedItem;
        this.albumSuggestedItem = albumSuggestedItem;
        this.songSuggestedItem = songSuggestedItem;
    }

    /**
     * Gets artist suggest list.
     *
     * @return the artist suggest list
     */
    public List<ArtistSuggestedItem> getArtistSuggestedItem() {

        return artistSuggestedItem;
    }

    /**
     * Sets artist suggest list.
     *
     * @param artistSuggestedItem the artist suggest list
     */
    public void setArtistSuggestedItem(List<ArtistSuggestedItem> artistSuggestedItem) {

        this.artistSuggestedItem = artistSuggestedItem;
    }

    /**
     * Gets album suggest list.
     *
     * @return the album suggest list
     */
    public List<AlbumSuggestedItem> getAlbumSuggestedItem() {

        return albumSuggestedItem;
    }

    /**
     * Sets album suggest list.
     *
     * @param albumSuggestedItem the album suggest list
     */
    public void setAlbumSuggestedItem(List<AlbumSuggestedItem> albumSuggestedItem) {

        this.albumSuggestedItem = albumSuggestedItem;
    }

    /**
     * Gets song suggest list.
     *
     * @return the song suggest list
     */
    public List<SongSuggestedItem> getSongSuggestedItem() {

        return songSuggestedItem;
    }

    /**
     * Sets song suggest list.
     *
     * @param songSuggestedItem the song suggest list
     */
    public void setSongSuggestedItem(List<SongSuggestedItem> songSuggestedItem) {

        this.songSuggestedItem = songSuggestedItem;
    }

}
