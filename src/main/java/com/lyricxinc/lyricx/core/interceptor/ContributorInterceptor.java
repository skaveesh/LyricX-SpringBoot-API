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

        //todo uncomment these in order to work auth
//        String authToken = (String) request.getAttribute("authToken");
//        FirebaseToken decodeToken;
//
//        try {
//            decodeToken = FirebaseAuth.getInstance(FirebaseConfig.getContributorFirebaseApp()).verifyIdToken(authToken);
//        } catch (Exception e) {
//            throw new ForbiddenCustomException("Provided credentials are not valid.");
//        }
//
//        if (!decodeToken.isEmailVerified())
//            throw new ForbiddenCustomException("Your account hasn't verified yet. Please verify.");
//
//        HttpSession session = request.getSession();
//        session.setAttribute("userId", decodeToken.getUid());

        //todo delete below after testing over
        HttpSession session = request.getSession();
        session.setAttribute("userId", "gjzXThtgTsUBcZMw0eSADOI3Fs73");

        return true;
    }

}
