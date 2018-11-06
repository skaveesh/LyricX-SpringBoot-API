package com.lyricxinc.lyricx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreServiceTest {

    @Autowired
    GenreService genreService;

    @Test
    public void findGenreByName() {
    }

    @Test
    public void getGenreById() {
    }

    @Test
    public void addGenre() {
        genreService.addGenre("Rap");
        genreService.addGenre("Hip-Hop");
    }
}