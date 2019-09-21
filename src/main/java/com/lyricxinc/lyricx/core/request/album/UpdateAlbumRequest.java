package com.lyricxinc.lyricx.core.request.album;

public class UpdateAlbumRequest {

    private long albumId;
    private long artistId;
    private String name;
    private String year;

    public long getAlbumId() {

        return albumId;
    }

    public void setAlbumId(long albumId) {

        this.albumId = albumId;
    }

    public long getArtistId() {

        return artistId;
    }

    public void setArtistId(long artistId) {

        this.artistId = artistId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getYear() {

        return year;
    }

    public void setYear(String year) {

        this.year = year;
    }

}
