package com.lyricxinc.lyricx.core;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContributorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //token handler in the request header
        String authToken = request.getHeader("Authorization");;

        if(authToken.equals("okay")) {
            System.out.println("HELLO CONTRIBURO" + authToken);
            return true;
        }else{
            System.out.println("NOT VALUD CONTRIBURO" + authToken);
            throw new ForbiddenCustomException("Provided token is not valid.");
        }


    }
}
