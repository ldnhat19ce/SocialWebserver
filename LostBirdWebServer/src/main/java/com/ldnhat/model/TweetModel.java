package com.ldnhat.model;

public class TweetModel extends AbstractModel{

    private String tweetStatus;
    private UserModel userModel;
    private String tweetImage;
    private RetweetModel retweetModel;
    private int isLike;
    private int status;

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public String getTweetStatus() {
        return tweetStatus;
    }

    public void setTweetStatus(String tweetStatus) {
        this.tweetStatus = tweetStatus;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getTweetImage() {
        return tweetImage;
    }

    public void setTweetImage(String tweetImage) {
        this.tweetImage = tweetImage;
    }

    public RetweetModel getRetweetModel() {
        return retweetModel;
    }

    public void setRetweetModel(RetweetModel retweetModel) {
        this.retweetModel = retweetModel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
