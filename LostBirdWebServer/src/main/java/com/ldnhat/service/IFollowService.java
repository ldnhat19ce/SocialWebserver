package com.ldnhat.service;

import com.ldnhat.model.FollowModel;

public interface IFollowService {

    Boolean checkUserIsFollow(int userSender, int userReceive);
    int countUserSender(int userSender);
    int countUserReceive(int userReceive);
    FollowModel save(FollowModel followModel);
    void deleteByUserSenderAndUserReceive(int userSender, int userReceive);
}
