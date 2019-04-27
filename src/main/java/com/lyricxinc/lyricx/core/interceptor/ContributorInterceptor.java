package com.lyricxinc.lyricx.core.interceptor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.lyricxinc.lyricx.core.config.FirebaseConfig;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ContributorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //token handler in the request header
        String authToken = request.getHeader("Authorization");

        if (!authToken.substring(0, 6).equals("Bearer"))
            throw new ForbiddenCustomException("Provided credentials are not valid.");

        authToken = authToken.substring(7);
        FirebaseToken decodeToken;

        try {
            decodeToken = FirebaseAuth.getInstance(FirebaseConfig.contributorFirebaseApp).verifyIdToken(authToken);
        } catch (Exception e) {
            throw new ForbiddenCustomException("Provided credentials are not valid.");
        }

//        TODO: uncomment this after testing is over
//        if (!decodeToken.isEmailVerified()) {
//            throw new ForbiddenCustomException("Your account hasn't verified yet. Please verify.");
//        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", decodeToken.getUid());

        return true;
    }
}
