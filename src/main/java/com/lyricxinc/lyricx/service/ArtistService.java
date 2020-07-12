package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.LyricxBaseException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.Genre;
import com.lyricxinc.lyricx.model.validator.group.OnArtistCreate;
import com.lyricxinc.lyricx.model.validator.group.OnArtistUpdate;
import com.lyricxinc.lyricx.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;

/**
 * The type Artist service.
 */
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ContributorService contributorService;
    private final GenreService genreService;
    private final ArtistGenreService artistGenreService;
    private final AmazonClientService amazonClientService;

    /**
     * The Artist default image url.
     */
    @Value("${com.lyricxinc.lyricx.artistImageUrl}")
    String artistDefaultImageUrl;

    /**
     * Instantiates a new Artist service.
     *
     * @param artistRepository    the artist repository
     * @param contributorService  the contributor service
     * @param genreService        the genre service
     * @param artistGenreService  the artist genre service
     * @param amazonClientService the amazon client service
     */
    @Autowired
    public ArtistService(ArtistRepository artistRepository, ContributorService contributorService, GenreService genreService, ArtistGenreService artistGenreService, AmazonClientService amazonClientService) {

        this.artistRepository = artistRepository;
        this.contributorService = contributorService;
        this.genreService = genreService;
        this.artistGenreService = artistGenreService;
        this.amazonClientService = amazonClientService;
    }

    /**
     * Gets artist by id.
     *
     * @param id the id
     * @return the artist by id
     */
    public Artist getArtistById(long id) {

        return artistRepository.findById(id).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_12.getErrorMessage(), LYRICX_ERR_12.name()));
    }

    /**
     * Gets artist by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the artist by surrogate key
     */
    public Artist getArtistBySurrogateKey(String surrogateKey) {

        return artistRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_12.getErrorMessage(), LYRICX_ERR_12.name()));
    }

    /**
     * Add artist.
     *
     * @param request     the request
     * @param payload     the payload
     * @param image       the image
     * @param genreIdList the genre id list
     */
    @Validated(OnArtistCreate.class)
    public void addArtist(final HttpServletRequest request, final @Valid Artist payload, final MultipartFile image, final List<Short> genreIdList) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setLastModifiedBy(contributor);

        if (image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
            payload.setImgUrl(imgUrl);
        }

        Artist savedArtist = this.artistRepository.save(payload);

        updateArtistGenreList(savedArtist, genreIdList);

    }

    /**
     * Update artist.
     *
     * @param request     the request
     * @param payload     the payload
     * @param image       the image
     * @param genreIdList the genre id list
     */
    @Validated(OnArtistUpdate.class)
    public void updateArtist(final HttpServletRequest request, final @Valid Artist payload, final MultipartFile image, final List<Short> genreIdList) {

        updateAlbumDetails(request, payload, image, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        Artist savedArtist = artistRepository.save(payload);

        try
        {
            updateArtistGenreList(savedArtist, genreIdList);
        } catch (LyricxBaseException ex)
        {
            //todo - log
        }
    }

    /**
     * Remove image.
     *
     * @param id the id
     */
    public void removeImage(long id) {
        //TODO
    }

    /**
     * Remove artist.
     *
     * @param id the id
     */
    public void removeArtist(long id) {

        artistRepository.deleteById(id);
    }

    /**
     * Find artists by surrogate keys list.
     *
     * @param surrogateKeySet the surrogate key set
     * @return the list
     */
    public List<Artist> findArtistsBySurrogateKeys(Set<String> surrogateKeySet){
        return artistRepository.findBySurrogateKeyIn(surrogateKeySet);
    }

    private String getArtistImgUrl(String surrogateKey) {

        return artistRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(LYRICX_ERR_26.getErrorMessage(), LYRICX_ERR_26.name()));
    }

    private void updateAlbumDetails(final HttpServletRequest request, final Artist payload, MultipartFile image, BiConsumer<Contributor, Artist> contributorArtistBiConsumer) {

        Artist oldArtist = this.getArtistBySurrogateKey(payload.getSurrogateKey());
        payload.setId(oldArtist.getId());

        if (payload.getAddedBy() == null || payload.getAddedBy().getId() == null)
        {
            payload.setAddedBy(oldArtist.getAddedBy());
        }

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        contributorArtistBiConsumer.accept(contributor, payload);
        payload.setLastModifiedBy(contributor);

        if(image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

            String oldImgUrl = null;

            try
            {
                oldImgUrl = getArtistImgUrl(payload.getSurrogateKey());
            } finally
            {
                //delete old song image from S3 bucket
                if (oldImgUrl != null)
                {
                    this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
                }
            }

            payload.setImgUrl(imgUrl);
        }
    }


    /**
     * Update artist genre list.
     *
     * @param artist      the artist
     * @param genreIdList the genre id list
     */
    public void updateArtistGenreList(Artist artist, List<Short> genreIdList){

        if(artist == null || genreIdList == null){
            throw new ForbiddenException(LYRICX_ERR_31.getErrorMessage(), LYRICX_ERR_31.name());
        }

        Set<Short> genreIdSet = new HashSet<>(genreIdList);

        List<Genre> genreList = genreService.findGenreByIds(genreIdSet);

        if(genreList.isEmpty()){
            throw new ForbiddenException(LYRICX_ERR_31.getErrorMessage(), LYRICX_ERR_31.name());
        }

        artistGenreService.createArtistGenre(artist, genreList);
    }

}
