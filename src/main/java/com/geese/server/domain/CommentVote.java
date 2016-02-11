package com.geese.server.domain;

/**
 * Created by ecrothers on 2015-11-08.
 */
public class CommentVote {
    private int gooseid;
    private int commentid;
    private int value;

    public CommentVote() {}

    private CommentVote(Builder builder) {
        this.gooseid = builder.gooseid;
        this.commentid = builder.commentid;
        this.value = builder.value;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    public int getCommentId() {
        return commentid;
    }

    public int getGooseId() {
        return gooseid;
    }

    public int getValue() {
        return value;
    }

    public static class Builder {
        private int gooseid;
        private int commentid;
        private int value;

        public Builder() {
        }

        public Builder(int gooseid, int commentid) {
            this.gooseid = gooseid;
            this.commentid = commentid;
        }

        public Builder(CommentVote commentVote) {
            this.gooseid = commentVote.gooseid;
            this.commentid = commentVote.commentid;
            this.value = commentVote.value;
        }

        public Builder gooseid(int gooseid) {
            this.gooseid = gooseid;
            return this;
        }

        public Builder commentid(int commentid) {
            this.commentid = commentid;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public CommentVote build() {
            return new CommentVote(this);
        }
    }
}
