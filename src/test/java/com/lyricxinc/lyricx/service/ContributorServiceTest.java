package com.lyricxinc.lyricx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContributorServiceTest {

    @Autowired
    ContributorService contributorService;

    @Test
    public void getContributorById() {
        contributorService.getContributorById("notavailablecontributor");
    }

    @Test
    public void addContributor() {

        contributorService.addContributor("skaveeshiiii12@gmail.com", new char[] {'s','k','v','e','e','s','h'}, "Samintha", "Kaveesh", "https://www.mylink.com");
    }

    @Test
    public void updateContributor(){
    }

    @Test
    public void removeContributorByEmail() {
        contributorService.removeContributor("john@gmail.com");
    }
}
