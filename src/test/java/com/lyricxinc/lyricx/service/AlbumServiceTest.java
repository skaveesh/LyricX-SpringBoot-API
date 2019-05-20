package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    ContributorService contributorService;

    @Test
    public void getAlbum() {
        Album album = albumService.getAlbumById(1L);
        System.out.println(album.getYear());
    }

    @Test
    public void addAlbum() {

    }

    @Test
    public void updateAlbum() {
    }

    @Test
    public void removeAlbum() {
    }
}
