package com.lyricxinc.lyricx.core.converter;

import com.lyricxinc.lyricx.core.dto.AlbumCreateUpdateRequestDTO;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

public class AlbumCreateUpdateRequestDTOToAlbumConverter implements Converter<AlbumCreateUpdateRequestDTO, Album> {

    @Override
    public Album convert(AlbumCreateUpdateRequestDTO source) {

        Artist artist = new Artist();
        artist.setSurrogateKey(source.getArtistSurrogateKey());

        Album album = new Album();
        album.setArtist(artist);
        album.setSurrogateKey(source.getSurrogateKey());
        album.setName(source.getName());
        album.setYear(source.getYear());
        album.setApprovedStatus(source.getApprovedStatus());

        return album;
    }

}
