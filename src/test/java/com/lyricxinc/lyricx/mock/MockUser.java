package com.lyricxinc.lyricx.mock;

import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpSession;

public class MockUser {

    public MockHttpServletRequest getMockHttpServletRequestForContributor(String userId) {

        MockHttpServletRequest mock = new MockHttpServletRequest();

        HttpSession session = mock.getSession();
        session.setAttribute("userId", userId);

        return mock;
    }

}
