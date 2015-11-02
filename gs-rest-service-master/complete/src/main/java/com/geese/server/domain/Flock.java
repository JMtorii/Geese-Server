package com.geese.server.domain;

/**
 * Created by JMtorii on 2015-10-06.
 */

public class Flock {
    private int id;
    private int authorid;
    private String name;
    private String description;
    private float latitude;
    private float longitude;
    private double radius;

    public Flock() {}

    private Flock(Builder builder) {}

    static Builder getBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public int getAuthorid() {
        return authorid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public double getRadius() {
        return radius;
    }

    public static class Builder {
        private int id;
        private int authorid;
        private String name;
        private String description;
        private float latitude;
        private float longitude;
        private double radius;

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

        // TODO: potentially check for non-null values
        public Flock build() {
            return new Flock(this);
        }
    }
}
