package com.lyricxinc.lyricx.core.interceptor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.lyricxinc.lyricx.core.config.FirebaseConfig;
import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

public class ChanterInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //token handler in the request header
        String authToken = request.getHeader("Authorization");
        FirebaseToken decodeToken = null;

        if (authToken.substring(0, 6).equals("Bearer"))
        {
            authToken = authToken.substring(7, authToken.length());

            try
            {
                decodeToken = FirebaseAuth.getInstance(FirebaseConfig.getChanterFirebaseApp()).verifyIdToken(authToken);
            } catch (Exception e)
            {
                throw new ForbiddenException(ErrorMessage.LYRICX_ERR_18, ErrorCode.LYRICX_ERR_18);
            }

            String uid = decodeToken.getUid();
            String email = decodeToken.getEmail();

            System.out.println("email: " + email + " uid: " + uid + " issuer: " + decodeToken.getIssuer());

            return true;
        }
        else
        {
            throw new ForbiddenException(ErrorMessage.LYRICX_ERR_18, ErrorCode.LYRICX_ERR_18);
        }
    }

}
