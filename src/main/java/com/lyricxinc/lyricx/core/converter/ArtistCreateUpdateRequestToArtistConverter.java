package com.lyricxinc.lyricx.core.converter;

import com.lyricxinc.lyricx.core.dto.ArtistCreateUpdateRequest;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

public class ArtistCreateUpdateRequestToArtistConverter implements Converter<ArtistCreateUpdateRequest, Artist> {

    @Override
    public Artist convert(ArtistCreateUpdateRequest source) {

        Artist artist = new Artist();
        artist.setSurrogateKey(source.getSurrogateKey());
        artist.setName(source.getName());
        artist.setApprovedStatus(source.getApprovedStatus());

        return artist;
    }

}
