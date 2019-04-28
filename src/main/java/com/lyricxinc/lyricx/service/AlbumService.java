package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.UUID;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AmazonClientService amazonClientService;
    private final ContributorService contributorService;
    private final ArtistService artistService;

    @Autowired
    public AlbumService(AmazonClientService amazonClientService, AlbumRepository albumRepository, ContributorService contributorService, ArtistService artistService) {
        this.amazonClientService = amazonClientService;
        this.albumRepository = albumRepository;
        this.contributorService = contributorService;
        this.artistService = artistService;
    }

    public Album getAlbum(long id) {
        return this.albumRepository.findById(id).orElse(null);
    }

    public void addAlbum(long artistId, String name, Year year, MultipartFile image, String contributorId) {

        Contributor contributor = contributorService.getContributorById(contributorId);

        Artist artist = artistService.getArtistById(artistId);

        if (artist != null) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);
            Album album = new Album(artist, year, name, imgUrl, contributor, contributor.isSeniorContributor(), UUID.randomUUID().toString().replace("-", ""));
            this.albumRepository.save(album);
        } else
            throw new ForbiddenCustomException("Requested artist cannot be found.");
    }

    public void updateAlbum(Album album, long artistId, String name, Year year, String contributorId) {

        Contributor contributor = contributorService.getContributorById(contributorId);

        if (!contributor.isSeniorContributor() && album.isApprovedStatus()) {
            throw new ForbiddenCustomException("Only senior contributors can update verified content.");
        }

        Artist artist = artistService.getArtistById(artistId);

        album.setArtist(artist);
        album.setName(name);
        album.setYear(year);

        this.albumRepository.save(album);
    }

    public void removeAlbum(long id) {
        this.albumRepository.deleteById(id);
    }
}
