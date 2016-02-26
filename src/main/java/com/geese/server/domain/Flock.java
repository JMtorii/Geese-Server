package com.geese.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

/**
 * Created by JMtorii on 2015-10-06.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flock {
    private int id;
    private int authorid;
    private String name;
    private String description;
    private float latitude;
    private float longitude;
    private double radius;
    private int score;
    private LocalDateTime createdTime;
    private LocalDateTime expireTime;
    private boolean favourited = false;

    public Flock() {}

    private Flock(Builder builder) {
        this.id = builder.id;
        this.authorid = builder.authorid;
        this.name = builder.name;
        this.description = builder.description;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.radius = builder.radius;
        this.score = builder.score;
        this.createdTime = builder.createdTime;
        this.expireTime = builder.expireTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean getFavourited() {
        return favourited;
    }

    public void setFavourited(boolean favourited) {
        this.favourited = favourited;
    }

    public static class Builder {
        private int id;
        private int authorid;
        private String name;
        private String description;
        private float latitude;
        private float longitude;
        private double radius;
        private int score;
        private LocalDateTime createdTime;
        private LocalDateTime expireTime;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder authorid(int authorid) {
            this.authorid = authorid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder latitude(float latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(float longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder radius(double radius) {
            this.radius = radius;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder expireTime(LocalDateTime expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        // TODO: potentially check for non-null values
        public Flock build() {
            return new Flock(this);
        }
    }
}
