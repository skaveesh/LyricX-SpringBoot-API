package com.lyricxinc.lyricx.core.converter.tomodel;

import com.lyricxinc.lyricx.core.dto.AlbumDTO;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

import java.time.Year;

public class AlbumDTOToAlbumConverter implements Converter<AlbumDTO, Album> {

    @Override
    public Album convert(AlbumDTO source) {

        Artist artist = new Artist();
        artist.setSurrogateKey(source.getArtistSurrogateKey());

        Album album = new Album();
        album.setArtist(artist);
        album.setSurrogateKey(source.getSurrogateKey());
        album.setName(source.getName());
        album.setYear(Year.parse(source.getYear()));
        album.setApprovedStatus(source.getApprovedStatus());

        return album;
    }

}
