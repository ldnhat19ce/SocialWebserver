package com.ldnhat.model;

public class FollowModel extends AbstractModel {

    private int userSender;
    private int userReceive;

    public int getUserSender() {
        return userSender;
    }

    public void setUserSender(int userSender) {
        this.userSender = userSender;
    }

    public int getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(int userReceive) {
        this.userReceive = userReceive;
    }
}
