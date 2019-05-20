package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumService albumService;
    private final LanguageService languageService;
    private final ContributorService contributorService;

    @Autowired
    public SongService(SongRepository songRepository, AlbumService albumService, LanguageService languageService, ContributorService contributorService) {
        this.songRepository = songRepository;
        this.albumService = albumService;
        this.languageService = languageService;
        this.contributorService = contributorService;
    }

    public Song getSongById(long id) {

        Song song = songRepository.findById(id).orElse(null);

        if(song == null)
            throw new ForbiddenCustomException("Requested song cannot be found.");

        return song;
    }

    public void addSong(HttpServletRequest request, String name, long albumId, String guitarKey, String beat, short languageId, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Album album = albumService.getAlbumById(albumId);

        Song song = new Song(name, album, guitarKey, beat, languageService.getLanguageById(languageId), keywords, lyrics, youTubeLink, spotifyLink, deezerLink, album.getImgUrl(), contributor, false);
        song.setSongUrl(UUID.randomUUID().toString().replace("-", ""));

        songRepository.save(song);
    }

    public void addSongWithAlbumArt(HttpServletRequest request, String name, long albumId, String guitarKey, String beat, String languageName, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink, String imgUrl) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Song song = new Song(name, albumService.getAlbumById(albumId), guitarKey, beat, languageService.findLanguageByName(languageName), keywords, lyrics, youTubeLink, spotifyLink, deezerLink, imgUrl, contributor, false);
        song.setSongUrl(UUID.randomUUID().toString().replace("-", ""));

        songRepository.save(song);
    }

    public void updateSong(Song song) {
        songRepository.save(song);
    }

    public void updateSongWithAlbumArt(){
        //TODO
    }

    public void removeAlbumArt(long id){
        //TODO
    }

    public void removeSong(long id) {
        songRepository.deleteById(id);
    }
}
