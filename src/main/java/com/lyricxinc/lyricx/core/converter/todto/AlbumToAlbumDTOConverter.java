package com.lyricxinc.lyricx.core.converter.todto;

import com.lyricxinc.lyricx.core.dto.AlbumDTO;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class AlbumToAlbumDTOConverter implements Converter<Album, AlbumDTO> {

    @Override
    public AlbumDTO convert(Album source) {

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setSurrogateKey(source.getSurrogateKey());
        albumDTO.setArtistSurrogateKey(source.getArtist().getSurrogateKey());
        albumDTO.setArtistName(source.getArtist().getName());
        albumDTO.setYear(source.getYear().toString());
        albumDTO.setName(source.getName());
        albumDTO.setImgUrl(source.getImgUrl());
        albumDTO.setAddedDate(source.getAddedDate().toString());
        albumDTO.setLastModifiedDate(source.getLastModifiedDate().toString());
        albumDTO.setAddedById(source.getAddedBy().getId());
        albumDTO.setLastModifiedById(source.getLastModifiedBy().getId());
        albumDTO.setApprovedStatus(source.isApprovedStatus());
        albumDTO.setSongsSurrogateKeys(source.getSongs().stream().map(Song::getSurrogateKey).collect(
                Collectors.toSet()));

        return albumDTO;
    }

}
