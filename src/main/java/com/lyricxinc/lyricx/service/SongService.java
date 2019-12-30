package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumService albumService;
    private final LanguageService languageService;
    private final ContributorService contributorService;
    private final AmazonClientService amazonClientService;

    @Autowired
    public SongService(SongRepository songRepository, AlbumService albumService, LanguageService languageService, ContributorService contributorService, AmazonClientService amazonClientService) {

        this.songRepository = songRepository;
        this.albumService = albumService;
        this.languageService = languageService;
        this.contributorService = contributorService;
        this.amazonClientService = amazonClientService;
    }

    public Song getSongById(long id) {
        return songRepository.findById(id).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_10, ErrorCode.LYRICX_ERR_10));
    }

    public void addSong(HttpServletRequest request, String name, long albumId, String guitarKey, String beat, short languageId, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Album album = albumService.getAlbumById(albumId);

//        Song song = new Song(name, album, guitarKey, beat, languageService.getLanguageById(languageId), keywords, lyrics, youTubeLink, spotifyLink, deezerLink, album.getImgUrl(), contributor, false);


//        songRepository.save(song);
    }

    public void addSong(HttpServletRequest request, String name, long albumId, String guitarKey, String beat, String languageName, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.SONG_FOLDER);

//        Song song = new Song(name, albumService.getAlbumById(albumId), guitarKey, beat, languageService.findLanguageByName(languageName), keywords, lyrics, youTubeLink, spotifyLink, deezerLink, imgUrl, contributor, false);


//        songRepository.save(song);
    }

    public void updateSong(HttpServletRequest request, long songId, String name, long albumId, String guitarKey, String beat, short languageId, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Song song = this.getSongById(songId);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, song);

        Album album = albumService.getAlbumById(albumId);

        Language language = languageService.getLanguageById(languageId);

        song.setName(name);
        song.setAlbum(album);
        song.setGuitarKey(guitarKey);
        song.setBeat(beat);
        song.setLanguage(language);
        song.setKeywords(keywords);
        song.setLyrics(lyrics);
        song.setYouTubeLink(youTubeLink);
        song.setSpotifyLink(spotifyLink);
        song.setDeezerLink(deezerLink);

        songRepository.save(song);
    }

    public void updateSong(HttpServletRequest request, long songId, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Song song = this.getSongById(songId);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, song);

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.SONG_FOLDER);

        //delete old song image from S3 bucket
        this.amazonClientService.deleteFileFromS3Bucket(song.getImgUrl(), AmazonClientService.S3BucketFolders.SONG_FOLDER);

        song.setImgUrl(imgUrl);

        songRepository.save(song);
    }

    public void removeAlbumArt(HttpServletRequest request, long songId) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Song song = this.getSongById(songId);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, song);

        //delete old song image from S3 bucket
        this.amazonClientService.deleteFileFromS3Bucket(song.getImgUrl(), AmazonClientService.S3BucketFolders.SONG_FOLDER);

        song.setImgUrl(song.getAlbum().getImgUrl());

        songRepository.save(song);
    }

    public void removeSong(HttpServletRequest request, long id) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Song song = this.getSongById(id);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, song);

        songRepository.deleteById(id);
    }

}
