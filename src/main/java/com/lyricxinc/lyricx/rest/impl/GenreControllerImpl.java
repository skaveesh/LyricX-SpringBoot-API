package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.dto.GenreDTO;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.rest.controller.GenreController;
import com.lyricxinc.lyricx.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage.SUCCESS;


@RestController
public class GenreControllerImpl implements GenreController {

    private final GenreService genreService;
    private final HttpResponse httpResponse;

    @Autowired
    public GenreControllerImpl(GenreService genreService, HttpResponse httpResponse) {
        this.genreService = genreService;
        this.httpResponse = httpResponse;
    }

    @Override
    public ResponseEntity<HttpResponseData> getAllGenres(HttpServletRequest request) {

        List<GenreDTO> x = genreService.getAllGenres();

        System.out.println(x);
        System.out.println("genre" + httpResponse.hashCode());

        return httpResponse.returnResponse(HttpStatus.OK, SUCCESS.getSuccessMessage(), null, x);
    }

}
