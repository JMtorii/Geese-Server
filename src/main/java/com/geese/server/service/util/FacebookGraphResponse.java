package com.geese.server.service.util;

/**
 * Created by Ni on 2016-02-18.
 */
public class FacebookGraphResponse {
    private String id;
    private String name;
    private String email;

    public FacebookGraphResponse() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\": \"" + name + "\"" +
                ", \"id\":" + id + "\"" +
                ", \"email\":" + email + "\"" +
                "}";
    }
}
