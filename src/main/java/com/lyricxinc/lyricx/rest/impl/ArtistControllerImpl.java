package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.exception.ContributorSuspendedCustomException;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.rest.controller.ArtistController;
import com.lyricxinc.lyricx.service.AmazonClientService;
import com.lyricxinc.lyricx.service.ArtistService;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ArtistControllerImpl implements ArtistController {

    private final AmazonClientService amazonClientService;
    private final ArtistService artistService;
    private final ContributorService contributorService;

    @Autowired
    ArtistControllerImpl(AmazonClientService amazonClientService, ArtistService artistService, ContributorService contributorService) {
        this.amazonClientService = amazonClientService;
        this.artistService = artistService;
        this.contributorService = contributorService;
    }

    @Override
    public void addArtist(String name, MultipartFile image, long addedById) {
        Contributor contributor = contributorService.getContributorById(addedById);

        if (!contributor.isSuspendStatus()) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

            Artist artist = new Artist(name, imgUrl, contributor, contributor.isSeniorContributor());
            artistService.addArtist(artist);
        }else
            throw new ContributorSuspendedCustomException();
    }

}
