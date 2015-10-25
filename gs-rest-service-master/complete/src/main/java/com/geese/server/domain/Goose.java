package com.geese.server.domain;

/**
 * Created by JMtorii on 2015-10-06.
 */
public class Goose {
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

    static class Builder {
        //required
        private int id;
        private String name;
        private String email;
        private boolean verified;

        //optional
        private String password;
        private String salt;

        private Builder(int id, String name, String email, boolean verified) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.verified = verified;
        }

        Builder password(String password) {
            this.password = password;
            return this;
        }

        Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        Goose build() {
            return new Goose(this);
        }
    }
}
