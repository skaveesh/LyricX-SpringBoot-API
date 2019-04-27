package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.rest.controller.ContributorController;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ContributorControllerImpl implements ContributorController {

    private final ContributorService contributorService;
    private final HttpResponse httpResponse;

    @Autowired
    public ContributorControllerImpl(ContributorService contributorService, HttpResponse httpResponse) {
        this.contributorService = contributorService;
        this.httpResponse = httpResponse;
    }

    @Override
    public ResponseEntity<HttpResponseData> createAccount(String email, char[] password, String firstName, String lastName, String contactLink) {
        contributorService.addContributor(email, password, firstName, lastName, contactLink);
        return httpResponse.returnResponse("Account created successfully.", "", HttpStatus.OK);
    }

    //TODO: remove this method after testing
    @Override
    public ResponseEntity<HttpResponseData> getContTest(HttpServletRequest request) {
        System.out.println("________________get contributor test user is "+ request.getSession().getAttribute("userId"));
        return httpResponse.returnResponse("Testing successful.", "", HttpStatus.I_AM_A_TEAPOT);
    }


}
