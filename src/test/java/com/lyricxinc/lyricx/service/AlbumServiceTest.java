package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.socket.inbound.AlbumSuggest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
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

    }

    @Test
    public void addAlbum() {

        byte[] bytes = {0x00, 0x00, 0x00, 0x00, 0x01, 0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x01, 0x01, 0x01, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

        MockMultipartFile mockMultipartFile = new MockMultipartFile("eminem image", "imagemin.jpeg", "image/jpeg", bytes);


    }

    @Test
    public void searchAlbums() {

        albumService.searchAlbums("mock", 1, 5).forEach(album -> System.out.println(album.getName()));

    }

    @Test
    public void updateAlbum() {

    }

    @Test
    public void removeAlbum() {

    }

    @Test
    public void suggestAlbums(){

        albumService.suggestAlbums(new AlbumSuggest("mock")).forEach((x)-> System.out.println(x.getAlbumName() + " " + x.getSurrogateKey()));
    }

}
