package com.lyricxinc.lyricx.core.converter.todto;

import com.lyricxinc.lyricx.core.dto.ContributorDTO;
import com.lyricxinc.lyricx.core.dto.SongDTO;
import com.lyricxinc.lyricx.core.util.StringValidatorUtil;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

import static com.lyricxinc.lyricx.core.util.ListUtil.fromNullableSet;
import static com.lyricxinc.lyricx.core.util.ListUtil.hasItems;

public class SongToSongDTOConverter implements Converter<Song, SongDTO> {

    @Override
    public SongDTO convert(Song source) {

        SongDTO songDTO = new SongDTO();
        songDTO.setSurrogateKey(source.getSurrogateKey());
        songDTO.setName(source.getName());
        songDTO.setAlbumSurrogateKey(source.getAlbum().getSurrogateKey());
        songDTO.setAlbumName(source.getAlbum().getName());
        songDTO.setArtistSurrogateKeyOfTheAlbum(source.getAlbum().getArtist().getSurrogateKey());
        songDTO.setArtistNameOfTheAlbum(source.getAlbum().getArtist().getName());
        songDTO.setGuitarKey(source.getGuitarKey());
        songDTO.setBeat(source.getBeat());
        songDTO.setLanguageCode(source.getLanguage().getLanguageCode());
        songDTO.setKeywords(source.getKeywords());
        songDTO.setLyrics(source.getLyrics());
        songDTO.setYouTubeLink(source.getYouTubeLink());
        songDTO.setSpotifyLink(source.getSpotifyLink());
        songDTO.setDeezerLink(source.getDeezerLink());
        songDTO.setAppleMusicLink(source.getAppleMusicLink());
        songDTO.setImgUrl(source.getImgUrl());
        songDTO.setIsExplicit(source.getIsExplicit());

        ContributorDTO addedBy = new ContributorDTO();
        addedBy.setFirstName(source.getAddedBy().getFirstName());
        addedBy.setLastName(source.getAddedBy().getLastName());
        addedBy.setDescription(null);
        addedBy.setImgUrl(source.getAddedBy().getImgUrl());
        addedBy.setContactLink(null);
        addedBy.setSeniorContributor(source.getAddedBy().isSeniorContributor());
        addedBy.setAddedDate(null);
        addedBy.setLastModifiedDate(null);
        songDTO.setAddedBy(addedBy);

        ContributorDTO lastModifiedBy = new ContributorDTO();
        lastModifiedBy.setFirstName(source.getLastModifiedBy().getFirstName());
        lastModifiedBy.setLastName(source.getLastModifiedBy().getLastName());
        lastModifiedBy.setDescription(null);
        lastModifiedBy.setImgUrl(source.getLastModifiedBy().getImgUrl());
        lastModifiedBy.setContactLink(null);
        lastModifiedBy.setSeniorContributor(source.getLastModifiedBy().isSeniorContributor());
        lastModifiedBy.setAddedDate(null);
        lastModifiedBy.setLastModifiedDate(null);
        songDTO.setLastModifiedBy(lastModifiedBy);

        if(source.getPublishedBy() != null) {
            ContributorDTO publishedBy = new ContributorDTO();
            publishedBy.setFirstName(source.getPublishedBy().getFirstName());
            publishedBy.setLastName(source.getPublishedBy().getLastName());
            publishedBy.setDescription(null);
            publishedBy.setImgUrl(source.getPublishedBy().getImgUrl());
            publishedBy.setContactLink(null);
            publishedBy.setSeniorContributor(source.getPublishedBy().isSeniorContributor());
            publishedBy.setAddedDate(null);
            publishedBy.setLastModifiedDate(null);
            songDTO.setPublishedBy(publishedBy);
        }

        songDTO.setAddedDate(source.getAddedDate().toString());
        songDTO.setLastModifiedDate(source.getLastModifiedDate().toString());
        songDTO.setPublishedDate(StringValidatorUtil.toStringOrNull(source.getPublishedDate()));
        songDTO.setPublishedState(source.getPublishedState());
        songDTO.setSongModifiesRequestsAvailable(hasItems(source.getSongModifies()));
        songDTO.setArtistSurrogateKeyList(
                fromNullableSet(source.getArtistSongs()).map(artistSong -> artistSong.getArtist().getSurrogateKey()).collect(
                        Collectors.toList()));
        songDTO.setArtistNameList(
                fromNullableSet(source.getArtistSongs()).map(artistSong -> artistSong.getArtist().getName()).collect(
                        Collectors.toList()));
        songDTO.setGenreIdList(
                source.getSongGenres().stream().map(songGenre -> songGenre.getGenre().getId()).collect(
                        Collectors.toList()));

        return songDTO;
    }

}
