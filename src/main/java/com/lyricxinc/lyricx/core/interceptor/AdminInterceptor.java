package com.lyricxinc.lyricx.core.interceptor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.lyricxinc.lyricx.core.config.FirebaseConfig;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //token handler in the request header
        String authToken = request.getHeader("Authorization");
        FirebaseToken decodeToken = null;

        if (authToken.substring(0, 6).equals("Bearer")) {
            authToken = authToken.substring(7, authToken.length());

            boolean isEmailVerified = false;

            try {
                decodeToken = FirebaseAuth.getInstance(FirebaseConfig.adminFirebaseApp).verifyIdToken(authToken);
            } catch (Exception e) {
                throw new ForbiddenCustomException("Provided credentials are not valid.");
            }

            String uid = decodeToken.getUid();
            String email = decodeToken.getEmail();
            isEmailVerified = decodeToken.isEmailVerified();

            if (!isEmailVerified) {
                throw new ForbiddenCustomException("Your account hasn't verified yet. Please verify.");
            }

            System.out.println("email: " + email + " uid: " + uid + " issuer: " + decodeToken.getIssuer());

            return true;
        } else {
            throw new ForbiddenCustomException("Provided credentials are not valid.");
        }
    }
}
