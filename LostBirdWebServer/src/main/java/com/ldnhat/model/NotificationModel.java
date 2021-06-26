package com.ldnhat.model;

public class NotificationModel extends AbstractModel {

    private UserModel notificationFrom;
    private UserModel notificationFor;
    private String type;
    private int target;
    private int status;

    public UserModel getNotificationFrom() {
        return notificationFrom;
    }

    public void setNotificationFrom(UserModel notificationFrom) {
        this.notificationFrom = notificationFrom;
    }

    public UserModel getNotificationFor() {
        return notificationFor;
    }

    public void setNotificationFor(UserModel notificationFor) {
        this.notificationFor = notificationFor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
