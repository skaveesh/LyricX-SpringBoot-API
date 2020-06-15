package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongServiceTest {

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    ArtistService artistService;

    @Autowired
    LanguageService languageService;

    @Autowired
    ContributorService contributorService;

    @Test
    public void getSong() {

        Song song = songService.getSongById(1L);

        System.out.println(song.getLyrics());
        System.out.println(song.getLanguage().getLanguageName());
    }

    @Test
    public void addSong() {

        Album album = albumService.getAlbumById(1L);

        Artist artist = artistService.getArtistById(1);

        Language language = languageService.getLanguageByLanguageCode("en");

        Contributor contributor = contributorService.getContributorById("gjzXThtgTsUBcZMw0eSADOI3Fs73");

        String lyrics = "Lately I been, I been losing sleep\\nDreaming about the things that we could be\\nBut baby I been, I been prayin' hard\\nSaid no more counting dollars\\nWe'll be counting stars\\nYeah, we'll be counting stars\\nI see this life\\nLike a swinging vine\\nSwing my heart across the line\\nIn my faces flashing signs\\nSeek it out and ye shall find\\nThe old, but I'm not that old\\nYoung, but I'm not that bold\\nAnd I don't think the world is sold\\nI'm just doing what we're told\\nI, feel something so right\\nDoing the wrong thing\\nI, feel something so wrong\\nBut doing the right thing\\nI could lie, could lie, could lie\\nEverything that kills me makes me feel alive\\nLately I been, I been losing sleep\\nDreaming 'bout the things that we could be\\nBaby I been, I been prayin' hard\\nSaid no more counting dollars\\nWe'll be counting stars\\nLately I been, I been losing sleep\\nDreaming 'bout the things that we could be\\nBut baby I been, I been prayin' hard\\nSaid no more counting dollars\\nWe'll be, we'll be counting stars\\nI feel the love\\nAnd I feel it burn\\nDown this river every turn\\nHope is a four letter word\\nMake that money\\nWatch it burn\\nOld, but I'm not that old\\nYoung, but I'm not that bold\\nAnd I don't think the world is sold\\nI'm just doing what we're told\\nI, feel something so wrong\\nBut doing the right thing\\nI could lie, could lie, could lie\\nEverything that drowns me makes me wanna fly\\nLately I been, I been losing sleep\\nDreaming about the things that we could be\\nBaby I been, I been prayin' hard\\nSaid no more counting dollars\\nWe'll be counting stars\\nLately I been, I been losing sleep\\nDreaming 'bout the things that we could be\\nBut baby I been, I been prayin' hard\\nSaid no more counting dollars\\nWe'll be, we'll be counting stars\\nTake that money and watch it burn\\nSink in the river the lessons I learned\\nTake that money and watch it burn\\nSink in the river the lessons I learned\\nTake that money and watch it burn\\nSink in the river the lessons I learned\\nTake that money and watch it burn\\nSink in the river the lessons I learned\\nEverything that kills me makes me feel alive\\nLately I been, I been losing sleep\\nDreaming 'bout the things that we could be\\nBaby I been, I been prayin' hard\\nSaid no more counting dollars\\nWe'll be counting stars\\nLately I been, I been losing sleep\\nDreaming 'bout the things that we could be\\nBaby I been, I been prayin' hard\\nSaid no more counting dollars\\nWe'll be, we'll be counting stars";

        MockHttpServletRequest mock = new MockHttpServletRequest();

        HttpSession session = mock.getSession();
        session.setAttribute("userId", "gjzXThtgTsUBcZMw0eSADOI3Fs73");

//        songService.createSong(mock, new Song("Counting Stars",album,"G","6x4", language, "Native\n Counting stars", lyrics, "https://www.youtube.com/watch?v=hT_nvWreIhg", "https://open.spotify.com/track/6sy3LkhNFjJWlaeSMNwQ62", "https://www.deezer.com/us/track/65759979", "img url", true, contributor, false));

    }

    //    @Test
    //    public void updateSong() {
    //        String lyrics = "Lately I been, I been losing sleep\n" +
    //                "Dreaming about the things that we could be\n" +
    //                "But baby I been, I been prayin' hard\n" +
    //                "Said no more counting dollars\n" +
    //                "We'll be counting stars\n" +
    //                "Yeah, we'll be counting stars\n" +
    //                "I see this life\n" +
    //                "Like a swinging vine\n" +
    //                "Swing my heart across the line\n" +
    //                "In my faces flashing signs\n" +
    //                "Seek it out and ye shall find\n" +
    //                "The old, but I'm not that old\n" +
    //                "Young, but I'm not that bold\n" +
    //                "And I don't think the world is sold\n" +
    //                "I'm just doing what we're told\n" +
    //                "I, feel something so right\n" +
    //                "Doing the wrong thing\n" +
    //                "I, feel something so wrong\n" +
    //                "But doing the right thing\n" +
    //                "I could lie, could lie, could lie\n" +
    //                "Everything that kills me makes me feel alive\n" +
    //                "Lately I been, I been losing sleep\n" +
    //                "Dreaming 'bout the things that we could be\n" +
    //                "Baby I been, I been prayin' hard\n" +
    //                "Said no more counting dollars\n" +
    //                "We'll be counting stars\n" +
    //                "Lately I been, I been losing sleep\n" +
    //                "Dreaming 'bout the things that we could be\n" +
    //                "But baby I been, I been prayin' hard\n" +
    //                "Said no more counting dollars\n" +
    //                "We'll be, we'll be counting stars\n" +
    //                "I feel the love\n" +
    //                "And I feel it burn\n" +
    //                "Down this river every turn\n" +
    //                "Hope is a four letter word\n" +
    //                "Make that money\n" +
    //                "Watch it burn\n" +
    //                "Old, but I'm not that old\n" +
    //                "Young, but I'm not that bold\n" +
    //                "And I don't think the world is sold\n" +
    //                "I'm just doing what we're told\n" +
    //                "I, feel something so wrong\n" +
    //                "But doing the right thing\n" +
    //                "I could lie, could lie, could lie\n" +
    //                "Everything that drowns me makes me wanna fly\n" +
    //                "Lately I been, I been losing sleep\n" +
    //                "Dreaming about the things that we could be\n" +
    //                "Baby I been, I been prayin' hard\n" +
    //                "Said no more counting dollars\n" +
    //                "We'll be counting stars\n" +
    //                "Lately I been, I been losing sleep\n" +
    //                "Dreaming 'bout the things that we could be\n" +
    //                "But baby I been, I been prayin' hard\n" +
    //                "Said no more counting dollars\n" +
    //                "We'll be, we'll be counting stars\n" +
    //                "Take that money and watch it burn\n" +
    //                "Sink in the river the lessons I learned\n" +
    //                "Take that money and watch it burn\n" +
    //                "Sink in the river the lessons I learned\n" +
    //                "Take that money and watch it burn\n" +
    //                "Sink in the river the lessons I learned\n" +
    //                "Take that money and watch it burn\n" +
    //                "Sink in the river the lessons I learned\n" +
    //                "Everything that kills me makes me feel alive\n" +
    //                "Lately I been, I been losing sleep\n" +
    //                "Dreaming 'bout the things that we could be\n" +
    //                "Baby I been, I been prayin' hard\n" +
    //                "Said no more counting dollars\n" +
    //                "We'll be counting stars\n" +
    //                "Lately I been, I been losing sleep\n" +
    //                "Dreaming 'bout the things that we could be\n" +
    //                "Baby I been, I been prayin' hard\n" +
    //                "Said no more counting dollars\n" +
    //                "We'll be, we'll be counting stars";
    //
    //        Song song = songService.getSongById(1L);
    //
    //        song.setLyrics(lyrics.getBytes());
    //
    //        songService.updateSong(song);
    //    }

    @Test
    public void removeSong() {

    }

}
