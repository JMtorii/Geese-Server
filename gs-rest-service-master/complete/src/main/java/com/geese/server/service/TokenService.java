package com.geese.server.service;

import com.geese.server.GooseAuthentication;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ni on 2015-11-18.
 */

public interface TokenService {

    void addAuthentication(HttpServletResponse response, GooseAuthentication authentication);

    String getToken(GooseAuthentication authentication);

    Authentication getAuthentication(HttpServletRequest request);
}