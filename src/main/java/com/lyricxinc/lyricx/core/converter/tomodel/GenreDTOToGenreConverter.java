package com.lyricxinc.lyricx.core.converter.tomodel;

import com.lyricxinc.lyricx.core.dto.GenreDTO;
import com.lyricxinc.lyricx.model.Genre;
import org.springframework.core.convert.converter.Converter;

public class GenreDTOToGenreConverter implements Converter<Genre, GenreDTO> {

    @Override
    public GenreDTO convert(Genre source) {

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(source.getId());
        genreDTO.setGenreName(source.getGenreName());

        return genreDTO;
    }

}
