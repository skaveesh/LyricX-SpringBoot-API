package com.lyricxinc.lyricx.core.converter.tomodel;

import com.lyricxinc.lyricx.core.dto.GenreResponseDTO;
import com.lyricxinc.lyricx.model.Genre;
import org.springframework.core.convert.converter.Converter;

public class GenreToGenreResponseDTOConverter  implements Converter<Genre, GenreResponseDTO> {

    @Override
    public GenreResponseDTO convert(Genre source) {

        GenreResponseDTO genreResponseDTO = new GenreResponseDTO();
        genreResponseDTO.setId(source.getId());
        genreResponseDTO.setGenreName(source.getGenreName());

        return genreResponseDTO;
    }

}
