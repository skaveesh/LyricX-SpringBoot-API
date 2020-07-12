package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContributorServiceTest {

    @Autowired
    ContributorService contributorService;

    @Test
    public void getContributorById_invalidContributor() {

        try
        {
            contributorService.getContributorById("notavailablecontributor");
        } catch (ForbiddenException ex)
        {
            Assert.assertEquals(LYRICX_ERR_05.getErrorMessage(), ex.getCode());
        }
    }

    @Test
    public void getContributorById_nullContributorId() {

        try
        {
            contributorService.getContributorById(null);
        } catch (ForbiddenException ex)
        {
            Assert.assertEquals(LYRICX_ERR_04.getErrorMessage(), ex.getCode());
        }
    }

    @Test
    public void addContributor() {

        contributorService.addContributor("skaveeshiiii12@gmail.com", new char[]{'s', 'k', 'v', 'e', 'e', 's', 'h'}, "Samintha", "Kaveesh", "https://www.mylink.com");
    }

    @Test
    public void updateContributor() {

    }

    @Test
    public void removeContributorByEmail() {

        contributorService.removeContributor("john@gmail.com");
    }

}
