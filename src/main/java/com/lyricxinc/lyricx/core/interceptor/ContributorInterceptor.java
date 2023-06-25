package com.lyricxinc.lyricx.core.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Contributor interceptor.
 */
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
        //            throw new ForbiddenCustomException(ErrorMessage.LYRICX_ERR_17, ErrorCode.LYRICX_ERR_17);
        //        }
        //
        //        if (!decodeToken.isEmailVerified())
        //            throw new ForbiddenCustomException(ErrorMessage.LYRICX_ERR_19, ErrorCode.LYRICX_ERR_19);
        //
        //        HttpSession session = request.getSession();
        //        session.setAttribute("userId", decodeToken.getUid());

        //todo delete below after testing over
        HttpSession session = request.getSession();
        session.setAttribute("userId", "gjzXThtgTsUBcZMw0eSADOI3Fs73");

        return true;
    }

}
