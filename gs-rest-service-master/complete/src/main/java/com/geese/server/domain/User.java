package com.geese.server.domain;

/**
 * Created by JMtorii on 2015-10-12.
 */
public class User {
    private int id;
    private String email;
    private String password;

    public User() {}

    private User(Builder builder) {}

    public static Builder getBuilder() {
        return new Builder();
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        private int id;
        private String email;
        private String password;

        private Builder() {}

//        Builder id(int id) {
//            this.id = id;
//            return this;
//        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        // TODO: potentially check for non-null values
        public User build() {
            return new User(this);
        }
    }
}
