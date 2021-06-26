package com.ldnhat.model;

public class CommentModel extends AbstractModel {

    private String commentStatus;
    private UserModel commentBy;
    private TweetModel commentTweet;

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public UserModel getCommentBy() {

        return commentBy;
    }

    public void setCommentBy(UserModel commentBy) {
        this.commentBy = commentBy;
    }

    public TweetModel getCommentTweet() {
        return commentTweet;
    }

    public void setCommentTweet(TweetModel commentTweet) {
        this.commentTweet = commentTweet;
    }
}
