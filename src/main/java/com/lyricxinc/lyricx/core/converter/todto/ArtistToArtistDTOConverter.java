package com.lyricxinc.lyricx.core.converter.todto;

import com.lyricxinc.lyricx.core.dto.ArtistDTO;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class ArtistToArtistDTOConverter implements Converter<Artist, ArtistDTO> {

    @Override
    public ArtistDTO convert(Artist source) {

        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setSurrogateKey(source.getSurrogateKey());
        artistDTO.setName(source.getName());
        artistDTO.setApprovedStatus(source.isApprovedStatus());
        artistDTO.setGenreIdList(
                source.getArtistGenres().stream().map(artistGenre -> artistGenre.getGenre().getId()).collect(
                        Collectors.toList()));

        return artistDTO;
    }

}
