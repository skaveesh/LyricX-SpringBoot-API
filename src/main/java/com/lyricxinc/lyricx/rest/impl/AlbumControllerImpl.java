package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.exception.ContributorSuspendedCustomException;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.rest.controller.AlbumController;
import com.lyricxinc.lyricx.service.AlbumService;
import com.lyricxinc.lyricx.service.AmazonClientService;
import com.lyricxinc.lyricx.service.ArtistService;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;

@RestController
@RequestMapping("/storage")
public class AlbumControllerImpl implements AlbumController {

    private final AmazonClientService amazonClientService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final ContributorService contributorService;

    @Autowired
    AlbumControllerImpl(AmazonClientService amazonClientService, AlbumService albumService, ContributorService contributorService, ArtistService artistService) {
        this.amazonClientService = amazonClientService;
        this.albumService = albumService;
        this.contributorService = contributorService;
        this.artistService = artistService;
    }

    @Override
    public void addAlbum(long artistId, String name, String year, MultipartFile image, long addedById) {
        Contributor contributor = contributorService.getContributorById(addedById);

        if (!contributor.isSuspendStatus()) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

            Artist artist = artistService.getArtistById(artistId);

            if (artist != null) {
                Album album = new Album(artist, Year.parse(year), name, imgUrl, contributor, contributor.isSeniorContributor());
                albumService.addAlbum(album);
            } else
                throw new ForbiddenCustomException("Requested artist cannot be found.");

        } else
            throw new ContributorSuspendedCustomException();
    }
}
