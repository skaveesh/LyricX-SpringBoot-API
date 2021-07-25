package com.lyricxinc.lyricx.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.lyricxinc.lyricx.core.config.FirebaseConfig;
import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.core.util.StringValidatorUtil;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.repository.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;

@Service
@Transactional
public class ContributorService {

    private final ContributorRepository contributorRepository;

    /**
     * The Contributor default image url.
     */
    @Value("${com.lyricxinc.lyricx.contributorImageUrl}")
    String contributorDefaultImageUrl;

    /**
     * Instantiates a new Contributor service.
     *
     * @param contributorRepository the contributor repository
     */
    @Autowired
    public ContributorService(ContributorRepository contributorRepository) {

        this.contributorRepository = contributorRepository;
    }

    /**
     * Gets contributor by id.
     *
     * @param id the id
     * @return the contributor by id
     */
    public Contributor getContributorById(String id) {

        //todo validate id in similar scenarios
        return contributorRepository.findById(Optional.ofNullable(id).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_04))).orElseThrow(() -> new NotFoundException(LYRICX_ERR_05));
    }

    /**
     * Add contributor.
     *
     * @param email       the email
     * @param password    the password
     * @param firstName   the first name
     * @param lastName    the last name
     * @param contactLink the contact link
     */
    public void addContributor(String email, char[] password, String firstName, String lastName, String contactLink) {

        email = StringValidatorUtil.validateEmailAddress(email);
        firstName = StringValidatorUtil.validateName(firstName);
        lastName = StringValidatorUtil.validateName(lastName);
        contactLink = StringValidatorUtil.validateContactLink(contactLink);

        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setEmail(email);
        createRequest.setPassword(String.valueOf(password));
        createRequest.setDisplayName(firstName + " " + lastName);

        UserRecord userRecord;
        Contributor contributor;

        try
        {
            userRecord = FirebaseAuth.getInstance(FirebaseConfig.getContributorFirebaseApp()).createUser(createRequest);

            contributor = new Contributor(userRecord.getUid(), firstName, lastName, contributorDefaultImageUrl, contactLink);
            contributorRepository.save(contributor);

        } catch (Exception e)
        {
            throw new ForbiddenException(LYRICX_ERR_06);
        }
    }

    /**
     * Update contributor.
     *
     * @param contributor the contributor
     */
    public void updateContributor(Contributor contributor) {

        contributorRepository.save(contributor);
    }

    /**
     * Remove contributor.
     *
     * @param id the id
     */
    public void removeContributor(String id) {

        contributorRepository.deleteById(id);
    }

    /**
     * Gets contributor by http servlet request.
     *
     * @param request the request
     * @return the contributor by http servlet request
     */
    public Contributor getContributorByHttpServletRequest(HttpServletRequest request) {

        return getContributorById((String) request.getSession().getAttribute("userId"));
    }

    /**
     * verified contributor can update verified/non-verified artist
     * non-verified contributor only can update non-verified artist
     *
     * @param contributor the contributor
     * @param song the song
     */
    public void checkNonSeniorContributorEditsVerifiedContent(Contributor contributor, Song song) {

        if (!contributor.isSeniorContributor() && song.getPublishedState())
            throw new ForbiddenException(LYRICX_ERR_07);
    }

    /**
     * verified contributor can update verified/non-verified artist
     * non-verified contributor only can update non-verified artist
     *
     * @param contributor the contributor
     * @param artist the song
     */
    public void checkNonSeniorContributorEditsVerifiedContent(Contributor contributor, Artist artist) {

        if (!contributor.isSeniorContributor() && artist.isApprovedStatus())
            throw new ForbiddenException(LYRICX_ERR_08);
    }

    /**
     * verified contributor can update verified/non-verified artist
     * non-verified contributor only can update non-verified artist
     *
     * @param contributor the contributor
     * @param album the album
     */
    public void checkNonSeniorContributorEditsVerifiedContent(Contributor contributor, Album album) {

        if (!contributor.isSeniorContributor() && album.isApprovedStatus())
            throw new ForbiddenException(LYRICX_ERR_09);
    }

}
