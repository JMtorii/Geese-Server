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
    private LocalDateTime expireTime;

    public Comment() {}

    private Comment(Builder builder) {
        this.id = builder.id;
        this.postid = builder.postid;
        this.authorid = builder.authorid;
        this.text = builder.text;
        this.score = builder.score;
        this.createdTime = builder.createdTime;
        this.expireTime = builder.expireTime;
    }

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

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public static class Builder {
        //required
        private int id;
        private int postid;
        private int authorid;
        private String text;
        private int score;
        private LocalDateTime createdTime;

        //optional
        private LocalDateTime expireTime;

        public Builder() {}

        //create with defaults
        public Builder(int postid, int authorid, String text) {
            this.postid = postid;
            this.authorid = authorid;
            this.text = text;
            this.score = 0;
            this.createdTime = LocalDateTime.now();
        }

        public Builder(Comment comment) {
            this.id = comment.id;
            this.postid = comment.postid;
            this.authorid = comment.authorid;
            this.text = comment.text;
            this.score = comment.score;
            this.createdTime = comment.createdTime;
        }

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

        public Builder expireTime(LocalDateTime expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}
