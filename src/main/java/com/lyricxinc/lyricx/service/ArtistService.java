package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.LyricxBaseException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.Genre;
import com.lyricxinc.lyricx.model.socket.inbound.ArtistSuggest;
import com.lyricxinc.lyricx.model.socket.outbound.ArtistSuggestedItem;
import com.lyricxinc.lyricx.model.validator.group.OnArtistCreate;
import com.lyricxinc.lyricx.model.validator.group.OnArtistUpdate;
import com.lyricxinc.lyricx.repository.ArtistRepository;
import com.lyricxinc.lyricx.service.suggest.MediaSuggestFactory;
import com.lyricxinc.lyricx.service.suggest.MediaSuggestOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.function.BiConsumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;
import static com.lyricxinc.lyricx.service.suggest.MediaSuggestFactory.MediaType.ARTIST;
import static com.lyricxinc.lyricx.core.util.StringValidatorUtil.*;

/**
 * The type Artist service.
 */
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ContributorService contributorService;
    private final GenreService genreService;
    private final AmazonClientService amazonClientService;
    private ArtistGenreService artistGenreService;

    private final MediaSuggestFactory mediaSuggestFactory;


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
     * @param amazonClientService the amazon client service
     * @param mediaSuggestFactory the media suggest factory
     */
    @Autowired
    public ArtistService(ArtistRepository artistRepository, ContributorService contributorService, GenreService genreService, AmazonClientService amazonClientService, MediaSuggestFactory mediaSuggestFactory) {

        this.artistRepository = artistRepository;
        this.contributorService = contributorService;
        this.genreService = genreService;
        this.amazonClientService = amazonClientService;
        this.mediaSuggestFactory = mediaSuggestFactory;
    }

    @Autowired
    public void setArtistGenreService(ArtistGenreService artistGenreService) {

        this.artistGenreService = artistGenreService;
    }

    public ArtistGenreService getArtistGenreService() {

        return artistGenreService;
    }

    /**
     * Gets artist by id.
     *
     * @param id the id
     * @return the artist by id
     */
    public Artist getArtistById(long id) {

        return artistRepository.findById(id).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_12));
    }

    /**
     * Gets artist by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the artist by surrogate key
     */
    public Artist getArtistBySurrogateKey(String surrogateKey) {

        return artistRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_12));
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

        Objects.requireNonNull(payload);

        List<Genre> genreList = getGenreListFromGenreIdList(genreIdList);

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setLastModifiedBy(contributor);
        payload.setApprovedStatus(false);

        if (image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
            payload.setImgUrl(imgUrl);
        }
        else
        {
            payload.setImgUrl(artistDefaultImageUrl);
        }

        Artist newArtist = this.artistRepository.save(payload);

        artistGenreService.createArtistGenre(newArtist, genreList);
    }

    @Validated(OnArtistUpdate.class)
    public void updateArtist(final HttpServletRequest request, final @Valid Artist payload, final List<Short> genreIdList) {

        Objects.requireNonNull(payload);

        Artist existingArtist = updateArtistDetails(request, payload, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        Artist updatedArtist = artistRepository.save(existingArtist);

        this.updateNewGenres(updatedArtist, genreIdList);
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

        Objects.requireNonNull(payload);
        Objects.requireNonNull(image);

        Artist existingArtist = updateArtistDetails(request, payload, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
        String oldImgUrl = null;

        try
        {
            oldImgUrl = getArtistImgUrl(existingArtist.getSurrogateKey());
        } finally
        {
            //delete old song image from S3 bucket
            if (oldImgUrl != null)
            {
                this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
            }
        }

        existingArtist.setImgUrl(imgUrl);

        Artist updatedArtist = artistRepository.save(existingArtist);

        this.updateNewGenres(updatedArtist, genreIdList);
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

        return artistRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(LYRICX_ERR_26));
    }

    private Artist updateArtistDetails(final HttpServletRequest request, final Artist payload, BiConsumer<Contributor, Artist> contributorArtistBiConsumer) {

        Artist existingArtist = this.getArtistBySurrogateKey(payload.getSurrogateKey());

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorArtistBiConsumer.accept(contributor, existingArtist);

        //check non senior contributor tries to update the verified status
        if (!contributor.isSeniorContributor() && payload.isApprovedStatus() != null) {
            throw new ForbiddenException(LYRICX_ERR_33);
        }

        existingArtist.setLastModifiedBy(contributor);

        if (isStringNotEmpty(payload.getName())) {
            existingArtist.setName(payload.getName());
        }

        if (payload.isApprovedStatus() != null){
            existingArtist.setApprovedStatus(payload.isApprovedStatus());
        }

        return existingArtist;
    }

    private void updateNewGenres(Artist updatedArtist, List<Short> genreIdList){
        try
        {
            List<Genre> genreList = getGenreListFromGenreIdList(genreIdList);
            int removedItems = artistGenreService.removeAllArtistGenre(updatedArtist.getId());
            // todo log removed items
            artistGenreService.createArtistGenre(updatedArtist, genreList);
        } catch (LyricxBaseException ex)
        {
            //todo - log there is no genres to update
        }
    }

    private List<Genre> getGenreListFromGenreIdList(List<Short> genreIdList){

        if(genreIdList == null || genreIdList.isEmpty()){
            throw new ForbiddenException(LYRICX_ERR_31);
        }

        Set<Short> genreIdSet = new HashSet<>(genreIdList);

        List<Genre> genreList = genreService.findGenreByIds(genreIdSet);

        if(genreList.isEmpty()){
            throw new ForbiddenException(LYRICX_ERR_31);
        }

        return genreList;
    }

    /**
     * Suggest artist sorted set.
     *
     * @param artistSuggest the artist suggest
     * @return the sorted set
     */
    public SortedSet<ArtistSuggestedItem> suggestArtists(final ArtistSuggest artistSuggest) {

        MediaSuggestOperation albumSuggestOperation = mediaSuggestFactory.getMediaSuggestion(ARTIST);

        TreeSet<ArtistSuggestedItem> albumSuggestedItemTreeSet = new TreeSet<>(Comparator.comparing(ArtistSuggestedItem::getArtistName));
        albumSuggestedItemTreeSet.addAll(albumSuggestOperation.readMedia(HtmlUtils.htmlEscape(artistSuggest.getArtistName())));

        System.out.println(HtmlUtils.htmlEscape(artistSuggest.getArtistName()));

        if (albumSuggestedItemTreeSet.size() < 6)
        {
            albumSuggestedItemTreeSet.clear();
            albumSuggestedItemTreeSet.addAll(artistRepository.findArtistSuggestionUsingArtistName(artistSuggest.getArtistName()));
            albumSuggestOperation.createMedia(albumSuggestedItemTreeSet);

            while (albumSuggestedItemTreeSet.size() > 6)
            {
                albumSuggestedItemTreeSet.pollLast();
            }
        }

        return albumSuggestedItemTreeSet;
    }

}
