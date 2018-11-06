package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Song getSong(long id) {
        return songRepository.findById(id).orElse(null);
    }

    public void addSong(String name, long albumId, String guitarKey, String beat, String languageName, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink, String contributorEmail) {

        Song song = new Song(name, albumService.getAlbum(albumId), guitarKey, beat, languageService.findLanguageByName(languageName), keywords, lyrics, youTubeLink, spotifyLink, deezerLink, contributorService.getContributorByEmail(contributorEmail),false);
        songRepository.save(song);
    }

    public void updateSong(Song song) {
        songRepository.save(song);
    }

    public void removeSong(long id) {
        songRepository.deleteById(id);
    }
}
