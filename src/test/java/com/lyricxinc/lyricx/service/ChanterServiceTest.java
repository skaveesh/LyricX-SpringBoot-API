package com.lyricxinc.lyricx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChanterServiceTest {

    @Autowired
    ChanterService chanterService;

    @Test
    public void addChanter() {

        chanterService.addChanter("tok1");
        chanterService.addChanter("tok2");
        chanterService.addChanter("tok3");
    }

    @Test
    public void removeAppUser() {

        chanterService.removeAppUser("1r3tesg");
    }

}
