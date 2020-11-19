package com.lyricxinc.lyricx.core.converter;

import com.lyricxinc.lyricx.core.dto.SongCreateUpdateRequestDTO;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.core.convert.converter.Converter;

public class SongCreateUpdateRequestDTOToSongConverter implements Converter<SongCreateUpdateRequestDTO, Song> {

    @Override
    public Song convert(SongCreateUpdateRequestDTO source) {

        Album album = new Album();
        album.setSurrogateKey(source.getAlbumSurrogateKey());

        Language language = new Language();
        language.setLanguageCode(source.getLanguageCode());

        Song song = new Song();
        song.setName(source.getName());
        song.setAlbum(album);
        song.setGuitarKey(source.getGuitarKey());
        song.setBeat(source.getBeat());
        song.setLanguage(language);
        song.setKeywords(source.getKeywords());
        song.setLyrics(source.getLyrics());
        song.setYouTubeLink(source.getYouTubeLink());
        song.setSpotifyLink(source.getSpotifyLink());
        song.setDeezerLink(source.getDeezerLink());
        song.setIsExplicit(source.getIsExplicit());

        return song;
    }

}
