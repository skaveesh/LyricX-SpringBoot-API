package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.dto.ContributorDTO;
import com.lyricxinc.lyricx.core.exception.EntityConversionException;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.rest.controller.ContributorController;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.LYRICX_ERR_36;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage.*;

@RestController
public class ContributorControllerImpl implements ContributorController {

    private final ContributorService contributorService;
    private final HttpResponse httpResponse;
    private final ConversionService conversionService;

    @Autowired
    public ContributorControllerImpl(ContributorService contributorService, HttpResponse httpResponse, ConversionService conversionService) {

        this.contributorService = contributorService;
        this.httpResponse = httpResponse;
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<HttpResponseData> createAccount(String email, char[] password, String firstName, String lastName, String contactLink) {

        contributorService.addContributor(email, password, firstName, lastName, contactLink);
        return httpResponse.returnResponse(HttpStatus.OK, ACCOUNT_CREATE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> getContributor(final HttpServletRequest request) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        ContributorDTO dto = asContributorDTO(contributor);
        return httpResponse.returnResponse(HttpStatus.OK, SUCCESS.getSuccessMessage(), null, dto);
    }

    private ContributorDTO asContributorDTO(final Contributor contributor) {
        return Optional.ofNullable(conversionService.convert(contributor, ContributorDTO.class)).orElseThrow(() -> new EntityConversionException(LYRICX_ERR_36));
    }
}
