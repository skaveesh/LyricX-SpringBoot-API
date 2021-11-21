package com.lyricxinc.lyricx.core.converter.todto;

import com.lyricxinc.lyricx.core.dto.ArtistDTO;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class ArtistToArtistDTOConverter implements Converter<Artist, ArtistDTO> {

    @Override
    public ArtistDTO convert(Artist source) {

        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setSurrogateKey(source.getSurrogateKey());
        artistDTO.setName(source.getName());
        artistDTO.setImgUrl(source.getImgUrl());
        artistDTO.setAddedById(source.getAddedBy().getId());
        artistDTO.setLastModifiedById(source.getLastModifiedBy().getId());
        artistDTO.setApprovedStatus(source.isApprovedStatus());
        artistDTO.setAddedDate(source.getAddedDate().toString());
        artistDTO.setLastModifiedDate(source.getLastModifiedDate().toString());
        artistDTO.setGenreIdList(
                source.getArtistGenres().stream().map(artistGenre -> artistGenre.getGenre().getId()).collect(
                        Collectors.toList()));
        artistDTO.setAlbumsSurrogateKeyList(
                source.getAlbums().stream().map(Album::getSurrogateKey).collect(
                        Collectors.toList()));
        artistDTO.setArtistSongsSurrogateKeyList(
                source.getArtistSongs().stream().map(artistSong -> artistSong.getSong().getSurrogateKey()).collect(
                        Collectors.toList())
        );

        return artistDTO;
    }

}
