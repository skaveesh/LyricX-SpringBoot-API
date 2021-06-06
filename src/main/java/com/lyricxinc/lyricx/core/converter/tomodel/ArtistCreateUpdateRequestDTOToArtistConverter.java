package com.lyricxinc.lyricx.core.converter.tomodel;

import com.lyricxinc.lyricx.core.dto.ArtistCreateUpdateRequestDTO;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

public class ArtistCreateUpdateRequestDTOToArtistConverter implements Converter<ArtistCreateUpdateRequestDTO, Artist> {

    @Override
    public Artist convert(ArtistCreateUpdateRequestDTO source) {

        Artist artist = new Artist();
        artist.setSurrogateKey(source.getSurrogateKey());
        artist.setName(source.getName());
        artist.setApprovedStatus(source.getApprovedStatus());

        return artist;
    }

}
