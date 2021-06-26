package com.ldnhat.DAO;

import com.ldnhat.model.FollowModel;

public interface IFollowDAO extends GenericDAO<FollowModel> {

    FollowModel checkUserIsFollow(int userSender, int userReceive);
    int countUserSender(int userSender);
    int countUserReceive(int userReceive);
    Long save(FollowModel followModel);
    void deleteByUserSenderAndUserReceive(int userSender, int userReceive);
    FollowModel findOne(Long id);
}
