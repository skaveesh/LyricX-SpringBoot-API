package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        Contributor contributor = contributorService.getContributorByEmail("sammy@yahoo.com");

        //Artist newArtist = new Artist("OneRepublic","https://image.png",contributor,true);
        Artist newArtist = new Artist("EMINEM","https://image.png",contributor,true);
        artistService.addArtist(newArtist);
    }

    @Test
    public void addArtistWithSameName() {
        Contributor contributor = contributorService.getContributorByEmail("sammy@yahoo.com");
        Artist newArtist = new Artist("OneRepublic","https://image2.png",contributor,true);
        artistService.addArtist(newArtist);
    }

    @Test
    public void updateArtist(){
        Contributor contributor = contributorService.getContributorByEmail("john@gmail.com");
        Artist artist = artistService.getArtistById(2L);
        artist.setAddedBy(contributor);

        artistService.updateArtist(artist);
    }

    @Test
    public void removeArtist() {
        try {

            artistService.removeArtist(6L);
        }catch (Exception e){
            System.out.println("MESSAGE : "+e.getMessage());
        }
    }
}