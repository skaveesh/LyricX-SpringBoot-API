package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ContributorService contributorService;
    private final AmazonClientService amazonClientService;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ContributorService contributorService, AmazonClientService amazonClientService) {

        this.artistRepository = artistRepository;
        this.contributorService = contributorService;
        this.amazonClientService = amazonClientService;
    }

    public Artist getArtistById(long id) {

        Artist artist = artistRepository.findById(id).orElse(null);

        if (artist == null)
            throw new ForbiddenCustomException("Requested artist cannot be found.");

        return artist;
    }

    public void addArtist(HttpServletRequest request, String name, MultipartFile image) {

        Contributor contributor = contributorService.getContributorById((String) request.getSession().getAttribute("userId"));

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

        Artist artist = new Artist(name, imgUrl, contributor, contributor.isSeniorContributor());

        artist.setSurrogateKey(UUID.randomUUID().toString().replace("-", ""));

        artistRepository.save(artist);
    }

    public void updateArtist(HttpServletRequest request, Artist artist, String name) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, artist);

        artist.setName(name);

        artistRepository.save(artist);
    }

    public void updateArtist(HttpServletRequest request, Artist artist, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, artist);

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

        //delete old artist image from S3 bucket
        this.amazonClientService.deleteFileFromS3Bucket(artist.getImgUrl(), AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

        artist.setImgUrl(imgUrl);

        artistRepository.save(artist);
    }

    public void removeImage(long id) {
        //TODO
    }

    public void removeArtist(long id) {

        artistRepository.deleteById(id);
    }

}
