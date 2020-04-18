package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.ArtistGenre;
import com.lyricxinc.lyricx.repository.ArtistGenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistGenreServiceTest {

    @Autowired
    ArtistGenreService artistGenreService;

    @Autowired
    ArtistGenreRepository artistGenreRepository;

    @Test
    public void addArtistGenre() {

        artistGenreService.addArtistGenre(6L, (short) 1);
        artistGenreService.addArtistGenre(6L, (short) 2);
    }

    @Test
    public void removeArtistGenre() {

        artistGenreService.removeArtistGenre(4L, (short) 1);
        artistGenreService.removeArtistGenre(4L, (short) 2);


        //        System.out.println("RETURN VALUE : "+i);
    }

    @Test
    public void findArtistGenre() {

        ArtistGenre artistGenre = artistGenreRepository.findByArtist_IdAndGenre_Id(3L, (short) 2);

        System.out.println("RETURN VALUE : " + artistGenre.getId());
    }

}
