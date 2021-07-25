package com.lyricxinc.lyricx.core.converter.tomodel;

import com.lyricxinc.lyricx.core.dto.SongDTO;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.core.convert.converter.Converter;

public class SongDTOToSongConverter implements Converter<SongDTO, Song> {

    @Override
    public Song convert(SongDTO source) {

        Album album = new Album();
        album.setSurrogateKey(source.getAlbumSurrogateKey());

        Language language = new Language();
        language.setLanguageCode(source.getLanguageCode());

        Song song = new Song();
        song.setSurrogateKey(source.getSurrogateKey());
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
        song.setAppleMusicLink(source.getAppleMusicLink());
        song.setIsExplicit(source.getIsExplicit());

        return song;
    }

}
