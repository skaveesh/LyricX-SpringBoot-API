package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.rest.controller.HomeController;
import com.lyricxinc.lyricx.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Project lyricx
 * Created by skaveesh on 2018-10-18.
 */
@RestController
public class HomeControllerImpl implements HomeController {

    private final MetadataService metadataService;
    private final HttpResponse httpResponse;

    /**
     * Instantiates a new Home controller.
     *
     * @param metadataService the metadata service
     * @param httpResponse    the http response
     */
    @Autowired
    public HomeControllerImpl(MetadataService metadataService, HttpResponse httpResponse) {
        this.metadataService = metadataService;
        this.httpResponse = httpResponse;
    }

    @Override
    public String home() {

        return "LyricX Web Explorer";
    }

    @Override
    public ResponseEntity<HttpResponseData> getMetaData(HttpServletRequest request) {

        return httpResponse.returnResponse(HttpStatus.OK, null, null, metadataService.getAllMetadata());
    }

}
