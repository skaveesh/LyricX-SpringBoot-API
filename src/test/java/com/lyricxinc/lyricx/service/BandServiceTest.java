package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.BandChanter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandServiceTest {

    @Autowired
    BandService bandService;

    @Test
    public void getBandAppUsers(){
        for(BandChanter ap: bandService.getBandChanters(1)){
            System.out.println(ap.getChanter().getId());
        }
    }

    @Test
    public void addBand() {
        bandService.addBand("band3");
//        bandService.addBand("band2");
    }

    @Test
    public void removeBand() {
        bandService.removeBand(3L);
    }
}