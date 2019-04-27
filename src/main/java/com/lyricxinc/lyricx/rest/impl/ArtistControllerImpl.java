package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.rest.controller.ArtistController;
import com.lyricxinc.lyricx.service.AmazonClientService;
import com.lyricxinc.lyricx.service.ArtistService;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public void addArtist(HttpServletRequest request, String name, MultipartFile image) {

        Contributor contributor = contributorService.getContributorById((String) request.getSession().getAttribute("userId"));

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

        Artist artist = new Artist(name, imgUrl, contributor, contributor.isSeniorContributor());
        artistService.addArtist(artist);

    }

    @Override
    public void updateArtistName(HttpServletRequest request, long artistId, String name) {
        //verified contributor can update verified/non-verified artist
        //non-verified contributor only can update non-verified artist

        Contributor contributor = contributorService.getContributorById((String) request.getSession().getAttribute("userId"));

        Artist artist = artistService.getArtistById(artistId);

        if (!contributor.isSeniorContributor() && artist.isApprovedStatus()) {
            throw new ForbiddenCustomException("Only senior contributors can update verified content.");
        } else {
            artist.setName(name);
            artistService.updateArtist(artist);
        }

    }

    @Override
    public void updateArtistImage(HttpServletRequest request, long artistId, MultipartFile image) {
        //verified contributor can update verified/non-verified artist
        //non-verified contributor only can update non-verified artist

        Contributor contributor = contributorService.getContributorById((String) request.getSession().getAttribute("userId"));

        Artist artist = artistService.getArtistById(artistId);

        if (!contributor.isSeniorContributor() && artist.isApprovedStatus()) {
            throw new ForbiddenCustomException("Only senior contributors can update verified content.");
        } else {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

            //delete old artist image from S3 bucket
            this.amazonClientService.deleteFileFromS3Bucket(artist.getImgUrl(), AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

            artist.setImgUrl(imgUrl);
            artistService.updateArtist(artist);
        }
    }
}
