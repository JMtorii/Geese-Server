package com.geese.server;

import com.geese.server.domain.Goose;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Ni on 2015-11-19.
 */
public class GooseAuthentication implements Authentication {

    private final Goose goose;
    private boolean authenticated = true;

    public GooseAuthentication(Goose goose) {
        this.goose = goose;
    }

    @Override
    public String getName() {
        return goose.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return goose.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return goose.getPassword();
        }

    @Override
    public Goose getDetails() {
        return goose;
    }

    @Override
    public Object getPrincipal() {
        return goose.getUsername();
        }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
