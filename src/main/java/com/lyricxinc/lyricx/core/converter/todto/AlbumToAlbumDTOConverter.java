package com.lyricxinc.lyricx.core.converter.todto;

import com.lyricxinc.lyricx.core.dto.AlbumDTO;
import com.lyricxinc.lyricx.model.Album;
import org.springframework.core.convert.converter.Converter;

public class AlbumToAlbumDTOConverter implements Converter<Album, AlbumDTO> {

    @Override
    public AlbumDTO convert(Album source) {

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setSurrogateKey(source.getSurrogateKey());
        albumDTO.setArtistSurrogateKey(source.getArtist().getSurrogateKey());
        albumDTO.setName(source.getName());
        albumDTO.setYear(source.getYear());
        albumDTO.setApprovedStatus(source.isApprovedStatus());

        return albumDTO;
    }

}
