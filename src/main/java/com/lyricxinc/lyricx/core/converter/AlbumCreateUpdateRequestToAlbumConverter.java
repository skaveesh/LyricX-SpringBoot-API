package com.lyricxinc.lyricx.core.converter;

import com.lyricxinc.lyricx.core.dto.AlbumCreateUpdateRequest;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

public class AlbumCreateUpdateRequestToAlbumConverter implements Converter<AlbumCreateUpdateRequest, Album> {

    @Override
    public Album convert(AlbumCreateUpdateRequest source) {

        Artist artist = new Artist();
        artist.setSurrogateKey(source.getArtistSurrogateKey());

        Album album = new Album();
        album.setSurrogateKey(source.getSurrogateKey());
        album.setName(source.getName());
        album.setYear(source.getYear());
        album.setApprovedStatus(source.getApprovedStatus());

        return album;
    }

}
