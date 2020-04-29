package com.lyricxinc.lyricx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandChanterServiceTest {

    @Autowired
    BandChanterService bandChanterService;

    @Test
    public void addBandUser() {

        bandChanterService.addBandChanter(1L, 3L);
        bandChanterService.addBandChanter(2L, 3L);

        //bandChanterService.addBandChanter(2L,2L);
    }

    @Test
    public void removeBandUser() {

    }

}
