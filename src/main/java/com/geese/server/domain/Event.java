package com.geese.server.domain;

import java.time.LocalDateTime;

/**
 * (Post but with start/end time)
 * Created by ecrothers on 2015-11-08.
 */
public class Event {
    private int id;
    private int flockid;
    private int authorid;
    private String title;
    private String description;
    private int pinned;
    private int score;
    private LocalDateTime createdTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event() {}

    private Event(Builder builder) {
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

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }


    public int getFlockid() {
        return flockid;
    }

    public String getTitle() {
        return title;
    }

    public int getPinned() {
        return pinned;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
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
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder flockid(int flockid) {
            this.flockid = flockid;
            return this;
        }

        public Builder authorid(int authorid) {
            this.authorid = authorid;
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

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
