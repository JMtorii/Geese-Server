package com.geese.server.domain;

import java.time.LocalDateTime;

/**
 * Created by ecrothers on 2015-11-08.
 */
public class Comment {
    private int id;
    private int postid;
    private int authorid;
    private String text;
    private int score;
    private LocalDateTime createdTime;

    public Comment() {}

    private Comment(Builder builder) {}

    static Builder getBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public int getPostid() {
        return postid;
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
        private int postid;
        private int authorid;
        private String text;
        private int score;
        private LocalDateTime createdTime;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder postid(int postid) {
            this.postid = postid;
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

        public Comment build() {
            return new Comment(this);
        }
    }
}
