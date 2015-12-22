package com.geese.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ni on 2015-11-18.
 */
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Resource(name="gooseServiceImpl")
    private GooseService gooseService;

    @Autowired
    private TokenAuthenticationService authenticationService;

    public StatelessLoginFilter(String urlMapping) {
        super(new AntPathRequestMatcher(urlMapping));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        final Goose user = new ObjectMapper().readValue(request.getInputStream(), Goose.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        // Lookup the complete User object from the database and create an Authentication for it
        final Goose goose = gooseService.loadUserByUsername(authentication.getName());
        final GooseAuthentication gooseAuthentication = new GooseAuthentication(goose);

        // Add the custom token as HTTP header to the response
        authenticationService.addAuthentication(response, gooseAuthentication);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(gooseAuthentication);
    }
}