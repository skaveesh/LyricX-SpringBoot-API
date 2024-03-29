package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.socket.inbound.AlbumSuggest;
import com.lyricxinc.lyricx.model.socket.outbound.AlbumSuggestedItem;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;
import com.lyricxinc.lyricx.repository.AlbumRepository;
import com.lyricxinc.lyricx.service.amazon.AmazonClientService;
import com.lyricxinc.lyricx.service.suggest.MediaSuggestFactory;
import com.lyricxinc.lyricx.service.suggest.MediaSuggestOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BiConsumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;
import static com.lyricxinc.lyricx.core.util.StringValidatorUtil.isStringNotEmpty;
import static com.lyricxinc.lyricx.service.suggest.MediaSuggestFactory.MediaType.ALBUM;

/**
 * The type Album service.
 */
@Service
@Log4j2
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AmazonClientService amazonClientService;
    private final ContributorService contributorService;
    private final ArtistService artistService;

    private final MediaSuggestFactory mediaSuggestFactory;


    /**
     * The Album default image url.
     */
    @Value("${com.lyricxinc.lyricx.albumImageUrl}")
    String albumDefaultImageUrl;

    /**
     * Instantiates a new Album service.
     *
     * @param amazonClientService the amazon client service
     * @param albumRepository     the album repository
     * @param contributorService  the contributor service
     * @param artistService       the artist service
     */
    @Autowired
    public AlbumService(AmazonClientService amazonClientService, AlbumRepository albumRepository, ContributorService contributorService, ArtistService artistService) {

        this.amazonClientService = amazonClientService;
        this.albumRepository = albumRepository;
        this.contributorService = contributorService;
        this.artistService = artistService;
        this.mediaSuggestFactory = new MediaSuggestFactory();
    }

    /**
     * Gets album by id.
     *
     * @param id the id
     * @return the album by id
     */
    public Album getAlbumById(final Long id) {

        return albumRepository.findById(id).orElseThrow(() -> new NotFoundException(LYRICX_ERR_11));
    }

    /**
     * Gets album by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the album by surrogate key
     */
    public Album getAlbumBySurrogateKey(final String surrogateKey) {

        return albumRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(LYRICX_ERR_11));
    }

    /**
     * Search albums page.
     *
     * @param query      the query
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the page
     */
    public Page<Album> searchAlbums(final String query, int pageNumber, int pageSize) {

        return this.albumRepository.findByNameContainingIgnoreCase(query, PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    /**
     * Save album.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the album
     */
    public Album saveAlbum(final HttpServletRequest request, final @Valid Album payload, MultipartFile image) {

        if (payload.getSurrogateKey() == null || payload.getSurrogateKey().isEmpty()) {

            if(image == null) {
                log.error("Album art not present while creating an album");
                throw new ForbiddenException(LYRICX_ERR_41);
            }

            return this.addAlbum(request, payload, image);
        } else {
            if (image != null) {
                return this.updateAlbum(request, payload, image);
            } else {
                return this.updateAlbum(request, payload);
            }
        }
    }

    /**
     * Add album.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the album
     */
    @Validated(OnAlbumCreate.class)
    public Album addAlbum(final HttpServletRequest request, final @Valid Album payload, MultipartFile image) {

        Objects.requireNonNull(payload);

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setLastModifiedBy(contributor);
        payload.setApprovedStatus(false);
        payload.setArtist(artistService.getArtistBySurrogateKey(payload.getArtist().getSurrogateKey()));

        if (image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFoldersType.ALBUM_FOLDER);
            payload.setImgUrl(imgUrl);
        }
        else
        {
            payload.setImgUrl(albumDefaultImageUrl);
        }

        return this.albumRepository.save(payload);
    }

    /**
     * Update album.
     *
     * @param request the request
     * @param payload the payload
     * @return the album
     */
    @Validated(OnAlbumUpdate.class)
    public Album updateAlbum(final HttpServletRequest request, final @Valid Album payload) {

        Objects.requireNonNull(payload);

        Album existingAlbum = updateAlbumDetails(request, payload, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        return albumRepository.save(existingAlbum);
    }

    /**
     * Update album.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the album
     */
    @Validated(OnAlbumUpdate.class)
    public Album updateAlbum(final HttpServletRequest request, final @Valid Album payload, MultipartFile image) {

        Objects.requireNonNull(payload);
        Objects.requireNonNull(image);

        Album existingAlbum = updateAlbumDetails(request, payload, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFoldersType.ALBUM_FOLDER);
        String oldImgUrl = null;

        try
        {
            oldImgUrl = getAlbumImgUrl(existingAlbum.getSurrogateKey());
        } finally
        {
            //delete old song image from S3 bucket
            if (oldImgUrl != null)
            {
                this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFoldersType.SONG_FOLDER);
            }
        }
        existingAlbum.setImgUrl(imgUrl);

        return albumRepository.save(existingAlbum);
    }

    /**
     * Remove album art.
     *
     * @param id the id
     */
    public void removeAlbumArt(Long id) {
        //TODO
    }

    /**
     * Remove album.
     *
     * @param id the id
     */
    public void removeAlbum(Long id) {
        //
        //        albumRepository.deleteById(id);
    }

    /**
     * Reset album art.
     *
     * @param request the request
     * @param payload the payload
     */
    @Validated(OnAlbumUpdate.class)
    public void resetAlbumArt(final HttpServletRequest request, final @Valid Album payload) {

        Objects.requireNonNull(payload);

        // todo have to re-check
        //updateAlbumDetails(request, payload, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        payload.setImgUrl(albumDefaultImageUrl);

        albumRepository.save(payload);
    }

    /**
     * Suggest albums sorted set.
     *
     * @param albumSuggest the album suggest
     * @return the sorted set
     */
    public SortedSet<AlbumSuggestedItem> suggestAlbums(final AlbumSuggest albumSuggest) {

        MediaSuggestOperation albumSuggestOperation = mediaSuggestFactory.getMediaSuggestion(ALBUM);

        TreeSet<AlbumSuggestedItem> albumSuggestedItemTreeSet = new TreeSet<>(Comparator.comparing((album)-> album.getAlbumName() + '$' + album.getSurrogateKey()));
        albumSuggestedItemTreeSet.addAll(albumSuggestOperation.readMedia(HtmlUtils.htmlEscape(albumSuggest.getAlbumName())));

        if (albumSuggestedItemTreeSet.size() < 6)
        {
            albumSuggestedItemTreeSet.clear();
            albumSuggestedItemTreeSet.addAll(albumRepository.findAlbumSuggestionUsingAlbumName(albumSuggest.getAlbumName()));
            albumSuggestOperation.createMedia(albumSuggestedItemTreeSet);

            while (albumSuggestedItemTreeSet.size() > 6)
            {
                albumSuggestedItemTreeSet.pollLast();
            }
        }

        return albumSuggestedItemTreeSet;
    }

    private String getAlbumImgUrl(String surrogateKey) {

        return albumRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(LYRICX_ERR_25));
    }

    private Album updateAlbumDetails(final HttpServletRequest request, final Album payload, BiConsumer<Contributor, Album> contributorAlbumBiConsumer) {

        Album existingAlbum = getAlbumBySurrogateKey(payload.getSurrogateKey());

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorAlbumBiConsumer.accept(contributor, existingAlbum);

        //check non senior contributor tries to update the verified status
        if (!contributor.isSeniorContributor() && payload.isApprovedStatus() != null) {
            throw new ForbiddenException(LYRICX_ERR_32);
        }

        existingAlbum.setLastModifiedBy(contributor);

        if(payload.getArtist() != null && isStringNotEmpty(payload.getArtist().getSurrogateKey())){
            existingAlbum.setArtist(artistService.getArtistBySurrogateKey(payload.getArtist().getSurrogateKey()));
        }

        if(isStringNotEmpty(payload.getName())){
            existingAlbum.setName(payload.getName());
        }

        if(payload.getYear() != null){
            existingAlbum.setYear(payload.getYear());
        }

        if (payload.isApprovedStatus() != null){
            existingAlbum.setApprovedStatus(payload.isApprovedStatus());
        }

        return existingAlbum;
    }

}
