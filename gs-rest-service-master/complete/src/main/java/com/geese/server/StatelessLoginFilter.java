package com.geese.server;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Ni on 2015-11-18.
 */
public class StatelessLoginFilter extends GenericFilterBean {

    private final TokenAuthenticationService authenticationService;

    public StatelessLoginFilter(TokenAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            //HttpServletResponse httpResponse = (HttpServletResponse) response;
            Authentication authentication = authenticationService.getAuthentication(httpRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }