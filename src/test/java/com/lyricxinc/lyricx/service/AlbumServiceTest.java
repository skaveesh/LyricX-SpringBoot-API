package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.time.Year;

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

        MockHttpServletRequest mock = new MockHttpServletRequest();

        HttpSession session = mock.getSession();
        session.setAttribute("userId", "gjzXThtgTsUBcZMw0eSADOI3Fs73");

        byte[] bytes = {0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x01,0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x00,0x00,0x01,0x01,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x01,0x01,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x01,0x01,0x00,0x00,0x00,0x00,0x01,0x01,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};

        MockMultipartFile mockMultipartFile = new MockMultipartFile("eminem image", "imagemin.jpeg", "image/jpeg", bytes);

        albumService.addAlbum(mock, 7, "Mockingbird", Year.of(1997), mockMultipartFile);

    }

    @Test
    public void updateAlbum() {
    }

    @Test
    public void removeAlbum() {
    }
}
