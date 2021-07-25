package com.lyricxinc.lyricx.core.converter.tomodel;

import com.lyricxinc.lyricx.core.dto.ArtistDTO;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

public class ArtistDTOToArtistConverter implements Converter<ArtistDTO, Artist> {

    @Override
    public Artist convert(ArtistDTO source) {

        Artist artist = new Artist();
        artist.setSurrogateKey(source.getSurrogateKey());
        artist.setName(source.getName());
        artist.setApprovedStatus(source.getApprovedStatus());

        return artist;
    }

}
