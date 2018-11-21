package com.lyricxinc.lyricx.rest.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.lyricxinc.lyricx.core.ResponseData;
import com.lyricxinc.lyricx.core.StringValidator;
import com.lyricxinc.lyricx.core.config.FirebaseConfig;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.rest.controller.ContributorController;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ContributorControllerImpl implements ContributorController {

    private final ContributorService contributorService;

    @Autowired
    public ContributorControllerImpl(ContributorService contributorService) {
        this.contributorService = contributorService;
    }

    @Override
    public ResponseEntity<ResponseData> createAccount(String email, char password, String firstName, String lastName, String contactLink) {

        email = StringValidator.validateEmailAddress(email);
        firstName = StringValidator.validateName(firstName);
        lastName = StringValidator.validateName(lastName);
        contactLink = StringValidator.validateContactLink(contactLink);

        CreateRequest createRequest = new CreateRequest();
        createRequest.setEmail(email);
        createRequest.setPassword(String.valueOf(password));
        createRequest.setDisplayName(firstName + " " + lastName);

        Contributor contributor = null;

        try {
            UserRecord userRecord = FirebaseAuth.getInstance(FirebaseConfig.contributorFirebaseApp).createUser(createRequest);
            contributor = new Contributor(userRecord.getUid(), firstName, lastName, contactLink);
            contributorService.addContributor(contributor);
        }catch (Exception e) {
            if(!(e instanceof FirebaseAuthException) && contributor != null){
                contributorService.removeContributor(contributor.getId());
            }
            throw new ForbiddenCustomException("Something went wrong while creating your account.");
        }

        ResponseData responseData = new ResponseData(LocalDateTime.now(), "Account created successfully.", "");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
