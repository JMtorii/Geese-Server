package com.geese.server.domain;

import java.time.LocalDateTime;

/**
 * Created by ecrothers on 2015-11-08.
 */
public class Topic {
    private int id;
    private int flockid;
    private int authorid;
    private String title;
    private String description;
    private int pinned;
    private int score;
    private LocalDateTime createdTime;

    public Topic() {}

    private Topic(Builder builder) {}

    static Builder getBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public int getAuthorid() {
        return authorid;
    }

    public int getFlockid() {
        return flockid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPinned() {
        return pinned;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public static class Builder {
        private int id;
        private int flockid;
        private int authorid;
        private String title;
        private String description;
        private int pinned;
        private int score;
        private LocalDateTime createdTime;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder authorid(int authorid) {
            this.authorid = authorid;
            return this;
        }

        public Builder flockid(int flockid) {
            this.flockid = flockid;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder pinned(int pinned) {
            this.pinned = pinned;
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

        public Topic build() {
            return new Topic(this);
        }
    }
}
