package com.lyricxinc.lyricx.core.converter.todto;

import com.lyricxinc.lyricx.core.dto.ContributorCreateUpdateRequestDTO;
import com.lyricxinc.lyricx.core.dto.SongCreateUpdateRequestDTO;
import com.lyricxinc.lyricx.core.util.StringValidatorUtil;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

import static com.lyricxinc.lyricx.core.util.ListUtil.fromNullableSet;
import static com.lyricxinc.lyricx.core.util.ListUtil.hasItems;

public class SongToSongCreateUpdateRequestDTOConverter implements Converter<Song, SongCreateUpdateRequestDTO> {

    @Override
    public SongCreateUpdateRequestDTO convert(Song source) {

        SongCreateUpdateRequestDTO songCreateUpdateRequestDTO = new SongCreateUpdateRequestDTO();
        songCreateUpdateRequestDTO.setSurrogateKey(source.getSurrogateKey());
        songCreateUpdateRequestDTO.setName(source.getName());
        songCreateUpdateRequestDTO.setAlbumSurrogateKey(source.getAlbum().getSurrogateKey());
        songCreateUpdateRequestDTO.setGuitarKey(source.getGuitarKey());
        songCreateUpdateRequestDTO.setBeat(source.getBeat());
        songCreateUpdateRequestDTO.setLanguageCode(source.getLanguage().getLanguageCode());
        songCreateUpdateRequestDTO.setKeywords(source.getKeywords());
        songCreateUpdateRequestDTO.setLyrics(source.getLyrics());
        songCreateUpdateRequestDTO.setYouTubeLink(source.getYouTubeLink());
        songCreateUpdateRequestDTO.setSpotifyLink(source.getSpotifyLink());
        songCreateUpdateRequestDTO.setDeezerLink(source.getDeezerLink());
        songCreateUpdateRequestDTO.setImgUrl(source.getImgUrl());
        songCreateUpdateRequestDTO.setIsExplicit(source.getIsExplicit());

        ContributorCreateUpdateRequestDTO addedBy = new ContributorCreateUpdateRequestDTO();
        addedBy.setFirstName(source.getAddedBy().getFirstName());
        addedBy.setLastName(source.getAddedBy().getLastName());
        addedBy.setDescription(null);
        addedBy.setImgUrl(source.getAddedBy().getImgUrl());
        addedBy.setContactLink(null);
        addedBy.setSeniorContributor(source.getAddedBy().isSeniorContributor());
        addedBy.setAddedDate(null);
        addedBy.setLastModifiedDate(null);
        songCreateUpdateRequestDTO.setAddedBy(addedBy);

        ContributorCreateUpdateRequestDTO lastModifiedBy = new ContributorCreateUpdateRequestDTO();
        lastModifiedBy.setFirstName(source.getLastModifiedBy().getFirstName());
        lastModifiedBy.setLastName(source.getLastModifiedBy().getLastName());
        lastModifiedBy.setDescription(null);
        lastModifiedBy.setImgUrl(source.getLastModifiedBy().getImgUrl());
        lastModifiedBy.setContactLink(null);
        lastModifiedBy.setSeniorContributor(source.getLastModifiedBy().isSeniorContributor());
        lastModifiedBy.setAddedDate(null);
        lastModifiedBy.setLastModifiedDate(null);
        songCreateUpdateRequestDTO.setLastModifiedBy(lastModifiedBy);

        if(source.getPublishedBy() != null) {
            ContributorCreateUpdateRequestDTO publishedBy = new ContributorCreateUpdateRequestDTO();
            publishedBy.setFirstName(source.getPublishedBy().getFirstName());
            publishedBy.setLastName(source.getPublishedBy().getLastName());
            publishedBy.setDescription(null);
            publishedBy.setImgUrl(source.getPublishedBy().getImgUrl());
            publishedBy.setContactLink(null);
            publishedBy.setSeniorContributor(source.getPublishedBy().isSeniorContributor());
            publishedBy.setAddedDate(null);
            publishedBy.setLastModifiedDate(null);
            songCreateUpdateRequestDTO.setPublishedBy(publishedBy);
        }

        songCreateUpdateRequestDTO.setAddedDate(source.getAddedDate().toString());
        songCreateUpdateRequestDTO.setLastModifiedDate(source.getLastModifiedDate().toString());
        songCreateUpdateRequestDTO.setPublishedDate(StringValidatorUtil.toStringOrNull(source.getPublishedDate()));
        songCreateUpdateRequestDTO.setPublishedState(source.getPublishedState());
        songCreateUpdateRequestDTO.setSongModifiesRequestsAvailable(hasItems(source.getSongModifies()));
        songCreateUpdateRequestDTO.setArtistSurrogateKeyList(
                fromNullableSet(source.getArtistSongs()).map(artistSong -> artistSong.getArtist().getSurrogateKey()).collect(
                        Collectors.toList()));
        songCreateUpdateRequestDTO.setGenreIdList(
                source.getSongGenres().stream().map(songGenre -> songGenre.getGenre().getId()).collect(
                        Collectors.toList()));

        return songCreateUpdateRequestDTO;
    }

}
