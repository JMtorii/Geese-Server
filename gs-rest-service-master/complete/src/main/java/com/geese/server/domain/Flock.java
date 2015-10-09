package com.geese.server.domain;

/**
 * Created by JMtorii on 2015-10-06.
 */

// TODO
public class Flock {
    private int id;
    private int authorid;
    private String name;
    private String description;
    private float latitude;
    private float longitude;
    private double range;

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

    public double getRange() {
        return range;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setRange(double range) {
        this.range = range;
    }

    static class Builder {
        private int id;
        private int authorid;
        private String name;
        private String description;
        private float latitude;
        private float longitude;
        private double range;

        private Builder() {}

        Builder id(int id) {
            this.id = id;
            return this;
        }

        Builder authorid(int authorid) {
            this.authorid = authorid;
            return this;
        }

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder description(String description) {
            this.description = description;
            return this;
        }

        Builder latitude(float latitude) {
            this.latitude = latitude;
            return this;
        }

        Builder longitude(float longitude) {
            this.longitude = longitude;
            return this;
        }

        Builder range(double range) {
            this.range = range;
            return this;
        }

        // TODO: potentially check for non-null values
        Flock build() {
            return new Flock(this);
        }
    }
}
