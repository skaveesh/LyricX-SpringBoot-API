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
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;
import static com.lyricxinc.lyricx.service.suggest.MediaSuggestFactory.MediaType.ALBUM;

/**
 * The type Album service.
 */
@Validated
@Service
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

        return albumRepository.findById(id).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_11, ErrorCode.LYRICX_ERR_11));
    }

    /**
     * Gets album by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the album by surrogate key
     */
    public Album getAlbumBySurrogateKey(final String surrogateKey) {

        return albumRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_11, ErrorCode.LYRICX_ERR_11));
    }

    /**
     * Search albums list.
     *
     * @param keyword the keyword
     * @return list list
     */
    public List<Album> searchAlbums(final String keyword) {

        return this.albumRepository.findTop20ByNameIgnoreCaseContainingOrderByNameAsc(keyword);
    }

    /**
     * Add album.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     */
    @Validated(OnAlbumCreate.class)
    public void addAlbum(final HttpServletRequest request, final @Valid Album payload, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setLastModifiedBy(contributor);

        payload.setArtist(artistService.getArtistBySurrogateKey(payload.getArtist().getSurrogateKey()));

        if (image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);
            payload.setImgUrl(imgUrl);
        }
        else
        {
            payload.setImgUrl(albumDefaultImageUrl);
        }

        this.albumRepository.save(payload);
    }

    /**
     * Update album.
     *
     * @param request the request
     * @param payload the payload
     */
    @Validated(OnAlbumUpdate.class)
    public void updateAlbum(final HttpServletRequest request, final @Valid Album payload) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        albumRepository.save(payload);
    }

    /**
     * Update album.
     *
     * @param request      the request
     * @param payload      the payload
     * @param payloadImage the payload image
     */
    @Validated(OnAlbumUpdate.class)
    public void updateAlbum(final HttpServletRequest request, final @Valid Album payload, MultipartFile payloadImage) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        String imgUrl = this.amazonClientService.uploadFile(payloadImage, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

        String oldImgUrl = null;

        try
        {
            oldImgUrl = getAlbumImgUrl(payload.getSurrogateKey());
        } finally
        {
            //delete old song image from S3 bucket
            if (oldImgUrl != null)
            {
                this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFolders.SONG_FOLDER);
            }
        }
        payload.setImgUrl(imgUrl);

        albumRepository.save(payload);
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

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

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

        TreeSet<AlbumSuggestedItem> albumSuggestedItemTreeSet = new TreeSet<>(Comparator.comparing(AlbumSuggestedItem::getAlbumName));
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

    private void setArtistThroughSurrogateKey(final Album payload) {

        String artistSurrogateKey = payload.getArtist().getSurrogateKey();

        if (artistSurrogateKey != null)
        {
            payload.setArtist(artistService.getArtistBySurrogateKey(artistSurrogateKey));
        }
    }

    private String getAlbumImgUrl(String surrogateKey) {

        return albumRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(ErrorMessage.LYRICX_ERR_25, ErrorCode.LYRICX_ERR_25));
    }

    private void updateAlbumDetails(final HttpServletRequest request, final Album payload, Consumer<Contributor> contributorStatus) {

        Album oldAlbum = getAlbumBySurrogateKey(payload.getSurrogateKey());
        payload.setId(oldAlbum.getId());

        if (payload.getArtist() == null || payload.getArtist().getId() == null)
        {
            payload.setArtist(oldAlbum.getArtist());
        }
        else
        {
            this.setArtistThroughSurrogateKey(payload);
        }

        if (payload.getAddedBy() == null || payload.getAddedBy().getId() == null)
        {
            payload.setAddedBy(oldAlbum.getAddedBy());
        }

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        contributorStatus.accept(contributor);
        payload.setLastModifiedBy(contributor);
    }

}
