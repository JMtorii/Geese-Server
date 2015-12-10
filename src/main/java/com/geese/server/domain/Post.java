package com.geese.server.domain;

import java.time.LocalDateTime;

/**
 * Created by ecrothers on 2015-11-08.
 */
public class Post {
    private int id;
    private int topicid;
    private int authorid;
    private String text;
    private int score;
    private LocalDateTime createdTime;

    public Post() {}

    private Post(Builder builder) {}

    static Builder getBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public int getTopicid() {
        return topicid;
    }

    public int getAuthorid() {
        return authorid;
    }

    public String getText() {
        return text;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public static class Builder {
        private int id;
        private int topicid;
        private int authorid;
        private String text;
        private int score;
        private LocalDateTime createdTime;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder topicid(int topicid) {
            this.topicid = topicid;
            return this;
        }

        public Builder authorid(int authorid) {
            this.authorid = authorid;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
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

        public Post build() {
            return new Post(this);
        }
    }
}
