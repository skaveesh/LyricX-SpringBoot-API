package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ContributorService contributorService;

    @Autowired

    @Test
    public void getArtist() {
        //Artist artist = artistService.getArtistById(2L);
        //System.out.println(artist.getName());
    }

    @Test
    public void addArtist() {
        //Artist newArtist = new Artist("OneRepublic","https://image.png",contributor,true);
        MockHttpServletRequest mock = new MockHttpServletRequest();

        HttpSession session = mock.getSession();
        session.setAttribute("userId", "gjzXThtgTsUBcZMw0eSADOI3Fs73");

        byte[] bytes = {0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x01,0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x00,0x00,0x01,0x01,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x01,0x01,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x01,0x01,0x00,0x00,0x00,0x00,0x01,0x01,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x01,0x01,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};

        MockMultipartFile mockMultipartFile = new MockMultipartFile("eminem image", "imagemin.jpeg", "image/jpeg", bytes);

        artistService.addArtist(mock, "EMINEM", mockMultipartFile);
    }

//    @Test
//    public void addArtistWithSameName() {
//        Contributor contributor = contributorService.getContributorById("sammy@yahoo.com");
//        Artist newArtist = new Artist("OneRepublic","https://image2.png",contributor,true);
//        artistService.addArtist(newArtist);
//    }
//
//    @Test
//    public void updateArtist(){
//        Contributor contributor = contributorService.getContributorById("john@gmail.com");
//        Artist artist = artistService.getArtistById(2L);
//        artist.setAddedBy(contributor);
//
//        artistService.updateArtist(artist);
//    }

    @Test
    public void removeArtist() {
        try {

            artistService.removeArtist(6L);
        }catch (Exception e){
            System.out.println("MESSAGE : "+e.getMessage());
        }
    }
}
