package com.geese.server.domain;

import java.time.LocalDateTime;

/**
 * Created by ecrothers on 2015-11-08.
 */
public class Post {
    private int id;
    private int flockid;
    private int authorid;
    private String title;
    private String description;
    private boolean pinned;
    private int score;
    private LocalDateTime createdTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Post() {}

    private Post(Builder builder) {
        this.id = builder.id;
        this.authorid = builder.authorid;
        this.flockid = builder.flockid;
        this.title = builder.title;
        this.pinned = builder.pinned;
        this.description = builder.description;
        this.score = builder.score;
        this.createdTime = builder.createdTime;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

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

    public boolean getPinned() {
        return pinned;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public static class Builder {
        private int id;
        private int flockid;
        private int authorid;
        private String title;
        private String description;
        private boolean pinned;
        private int score;
        private LocalDateTime createdTime;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder() {

        }

        public Builder(int flockid, int authorid, String title, String description) {
            this.flockid = flockid;
            this.authorid = authorid;
            this.title = title;
            this.description = description;
            this.pinned = false;
            this.score = 0;
            this.createdTime = LocalDateTime.now();
        }

        public Builder(Post post) {
            this.id = post.id;
            this.flockid = post.flockid;
            this.authorid = post.authorid;
            this.title = post.title;
            this.description = post.description;
            this.pinned = post.pinned;
            this.score = post.score;
            this.createdTime = post.createdTime;
            this.startTime = post.startTime;
            this.endTime = post.endTime;
        }

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

        public Builder pinned(boolean pinned) {
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

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
