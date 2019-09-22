package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.request.contributor.ConTestRequest;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.rest.controller.ContributorController;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ContributorControllerImpl implements ContributorController {

    private final ContributorService contributorService;
    private final HttpResponse httpResponse;
    private static final Logger LOGGER = Logger.getLogger(ContributorControllerImpl.class.getName());

    @Autowired
    public ContributorControllerImpl(ContributorService contributorService, HttpResponse httpResponse) {

        this.contributorService = contributorService;
        this.httpResponse = httpResponse;
    }

    @Override
    public ResponseEntity<HttpResponseData> createAccount(String email, char[] password, String firstName, String lastName, String contactLink) {

        contributorService.addContributor(email, password, firstName, lastName, contactLink);
        return httpResponse.returnResponse(HttpStatus.OK, "Account created successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> getContTest(HttpServletRequest request, @RequestBody ConTestRequest payload) {

        LOGGER.log(Level.WARNING, payload.getName());
        return httpResponse.returnResponse(HttpStatus.OK, "Okay", payload.getName());
    }

    //TODO: remove this method after testing
    //    @Override
    //    public ResponseEntity<HttpResponseData> getContTest(HttpServletRequest request) {
    //
    //        LOGGER.log(Level.INFO, "Contributor test user ID is " + request.getSession().getAttribute("userId"));
    //
    //        List<Object> arr = new ArrayList<>();
    //        arr.add("abc");
    //        arr.add("def");
    //
    //        List<String> arr2 = new ArrayList<>();
    //        arr2.add("pqr");
    //        arr2.add("xyz");
    //
    //        arr.add(arr2);
    //
    //        return httpResponse.returnResponse(HttpStatus.I_AM_A_TEAPOT, "Testing successful.", arr);
    //    }


}
