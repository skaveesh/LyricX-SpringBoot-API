package com.lyricxinc.lyricx.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.lyricxinc.lyricx.core.validator.StringValidator;
import com.lyricxinc.lyricx.core.config.FirebaseConfig;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.repository.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
        return contributorRepository.findById(id).orElse(null);
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

        UserRecord userRecord = null;
        Contributor contributor = null;

        try {
            userRecord = FirebaseAuth.getInstance(FirebaseConfig.contributorFirebaseApp).createUser(createRequest);

            contributor = new Contributor(userRecord.getUid(), firstName, lastName, environment.getProperty("lyricx.contributor-image-url"), contactLink);
            contributorRepository.save(contributor);

        } catch (Exception e) {
            throw new ForbiddenCustomException("Something went wrong while creating your account. " + e.getMessage());
        }


    }

    public void updateContributor(Contributor contributor) {
        contributorRepository.save(contributor);
    }

    public void removeContributor(String id) {
        contributorRepository.deleteById(id);
    }
}
