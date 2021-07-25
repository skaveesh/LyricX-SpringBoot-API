package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * The interface Contributor controller.
 */
@RequestMapping("contributor")
public interface ContributorController {

    /**
     * Create account response entity.
     *
     * @param email       the email
     * @param password    the password
     * @param firstName   the first name
     * @param lastName    the last name
     * @param contactLink the contact link
     * @return the response entity
     */
    @PostMapping(value = "register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<HttpResponseData> createAccount(String email, char[] password, String firstName, String lastName, String contactLink);

    /**
     * Gets contributor.
     *
     * @param request the request
     * @return the contributor
     */
    @GetMapping(value = "get")
    ResponseEntity<HttpResponseData> getContributor(HttpServletRequest request);

}
