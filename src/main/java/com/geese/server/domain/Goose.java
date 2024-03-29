package com.geese.server.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by JMtorii on 2015-10-06.
 */
public class Goose implements UserDetails {
    private int id;
    private String name;
    private String email;
    private boolean verified;
    private String password;
    private String salt;

    public Goose() {}

    private Goose(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.verified = builder.verified;
        this.password = builder.password;
        this.salt = builder.salt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getVerified() {
        return verified;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean isEnabled() {
        return getVerified();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public static class Builder {
        //required
        private int id;
        private String name;
        private String email;
        private boolean verified;

        //optional
        private String password;
        private String salt;

        public Builder(int id, String name, String email, boolean verified) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.verified = verified;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public Goose build() {
            return new Goose(this);
        }
    }
}
