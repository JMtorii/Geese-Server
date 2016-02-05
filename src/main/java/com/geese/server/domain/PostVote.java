package com.geese.server.domain;

/**
 * Created by ecrothers on 2015-11-08.
 */
public class PostVote {
    private int gooseid;
    private int postid;
    private int value;

    public PostVote() {}

    private PostVote(Builder builder) {
        this.gooseid = builder.gooseid;
        this.postid = builder.postid;
        this.value = builder.value;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    public int getPostId() {
        return postid;
    }

    public int getGooseId() {
        return gooseid;
    }

    public int getValue() {
        return value;
    }

    public static class Builder {
        private int gooseid;
        private int postid;
        private int value;

        public Builder() {
        }

        public Builder(int gooseid, int postid) {
            this.gooseid = gooseid;
            this.postid = postid;
        }

        public Builder(PostVote postVote) {
            this.gooseid = postVote.gooseid;
            this.postid = postVote.postid;
            this.value = postVote.value;
        }

        public Builder gooseid(int gooseid) {
            this.gooseid = gooseid;
            return this;
        }

        public Builder postid(int postid) {
            this.postid = postid;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public PostVote build() {
            return new PostVote(this);
        }
    }
}
