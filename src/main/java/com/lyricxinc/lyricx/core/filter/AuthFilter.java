package com.lyricxinc.lyricx.core.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * The type Auth filter.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //todo uncomment this to in order to work auth
        //        if(!((HttpServletRequest) servletRequest).getRequestURI().startsWith("/contributor/register")) {
        //
        //            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        //
        //            //token handler in the request header
        //            String authToken = httpRequest.getHeader("Authorization");
        //
        //            if (!authToken.substring(0, 6).equals("Bearer"))
        //                throw new ForbiddenCustomException("Provided credentials are not valid.");
        //
        //            authToken = authToken.substring(7);
        //
        //            servletRequest.setAttribute("authToken", authToken);
        //
        //        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}
