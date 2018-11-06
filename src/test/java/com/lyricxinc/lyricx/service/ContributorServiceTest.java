package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Contributor;
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

    private Contributor contributorSammy = new Contributor("sammy@gmail.com","12345678","Sammy","Kaveesh","hello world","http://pro-image.jpg","https://fb.com/sammy",false,false);
    private Contributor contributorJohn = new Contributor("john@gmail.com","12345678","John","Daee","hello world","http://pro-image.jpg","https://fb.com/john",true,false);

    @Test
    public void getContributorByEmail() {
    }

    @Test
    public void addContributor() {
        contributorService.addContributor(contributorJohn);
    }

    @Test
    public void updateContributor(){
        Contributor updateContributorSammy = contributorService.getContributorByEmail("sammy@gmail.com");
        updateContributorSammy.setEmail("sammy@yahoo.com");
        updateContributorSammy.setDescription("hello world and moon");
        contributorService.updateContributor(updateContributorSammy);
    }

    @Test
    public void removeContributerByEmail() {
        contributorService.removeContributerByEmail("john@gmail.com");
    }
}