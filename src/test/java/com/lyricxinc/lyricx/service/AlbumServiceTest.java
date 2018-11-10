package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Year;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistService artistService;

    @Autowired ContributorService contributorService;

    @Test
    public void getAlbum() {
        Album album = albumService.getAlbum(1L);
        System.out.println(album.getYear());
    }

    @Test
    public void addAlbum() {
        Artist artist = artistService.getArtistById(1L);
        Year year = Year.parse("2014");
        Contributor contributor = contributorService.getContributorByEmail("sammy@yahoo.com");
        Album newAlbum = new Album(artist, year, "Native", "https://album.jpg", contributor, true);
        albumService.addAlbum(newAlbum);
    }

    @Test
    public void updateAlbum() {
    }

    @Test
    public void removeAlbum() {
    }
}