package com.lyricxinc.lyricx.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.lyricxinc.lyricx.core.config.FirebaseConfig;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.core.validator.StringValidator;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.repository.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ContributorService {

    private final Environment environment;
    private final ContributorRepository contributorRepository;

    @Autowired
    public ContributorService(ContributorRepository contributorRepository, Environment environment) {

        this.contributorRepository = contributorRepository;
        this.environment = environment;
    }

    public Contributor getContributorById(String id) {

        if(id == null)
            throw new ForbiddenCustomException("User id is empty");

        Contributor contributor = contributorRepository.findById(id).orElse(null);

        if (contributor == null)
            throw new ForbiddenCustomException("Contributor cannot be found.");

        return contributor;
    }

    public void addContributor(String email, char[] password, String firstName, String lastName, String contactLink) {

        email = StringValidator.validateEmailAddress(email);
        firstName = StringValidator.validateName(firstName);
        lastName = StringValidator.validateName(lastName);
        contactLink = StringValidator.validateContactLink(contactLink);

        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setEmail(email);
        createRequest.setPassword(String.valueOf(password));
        createRequest.setDisplayName(firstName + " " + lastName);

        UserRecord userRecord;
        Contributor contributor;

        try
        {
            userRecord = FirebaseAuth.getInstance(FirebaseConfig.getContributorFirebaseApp()).createUser(createRequest);

            contributor = new Contributor(userRecord.getUid(), firstName, lastName, environment.getProperty("lyricx.contributor-image-url"), contactLink);
            contributorRepository.save(contributor);

        } catch (Exception e)
        {
            throw new ForbiddenCustomException("Something went wrong while creating your account. " + e.getMessage());
        }
    }

    public void updateContributor(Contributor contributor) {

        contributorRepository.save(contributor);
    }

    public void removeContributor(String id) {

        contributorRepository.deleteById(id);
    }

    public Contributor getContributorByHttpServletRequest(HttpServletRequest request) {

        return getContributorById((String) request.getSession().getAttribute("userId"));
    }

    /**
     * verified contributor can update verified/non-verified artist
     * non-verified contributor only can update non-verified artist
     *
     * @param contributor
     * @param song
     */
    public void checkNonSeniorContributorEditsVerifiedContent(Contributor contributor, Song song) {

        if (!contributor.isSeniorContributor() && song.isPublishedState())
            throw new ForbiddenCustomException("Only senior contributors can update verified artist.");
    }

    /**
     * verified contributor can update verified/non-verified artist
     * non-verified contributor only can update non-verified artist
     *
     * @param contributor
     * @param artist
     */
    public void checkNonSeniorContributorEditsVerifiedContent(Contributor contributor, Artist artist) {

        if (!contributor.isSeniorContributor() && artist.isApprovedStatus())
            throw new ForbiddenCustomException("Only senior contributors can update verified artist.");
    }

    /**
     * verified contributor can update verified/non-verified artist
     * non-verified contributor only can update non-verified artist
     *
     * @param contributor
     * @param album
     */
    public void checkNonSeniorContributorEditsVerifiedContent(Contributor contributor, Album album) {

        if (!contributor.isSeniorContributor() && album.isApprovedStatus())
            throw new ForbiddenCustomException("Only senior contributors can update verified album.");
    }

}
