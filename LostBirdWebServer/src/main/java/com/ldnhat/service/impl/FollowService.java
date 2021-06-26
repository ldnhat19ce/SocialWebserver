package com.ldnhat.service.impl;

import com.ldnhat.DAO.IFollowDAO;
import com.ldnhat.DAO.impl.FollowDAO;
import com.ldnhat.model.FollowModel;
import com.ldnhat.service.IFollowService;

import java.sql.Timestamp;

public class FollowService implements IFollowService {

    private IFollowDAO followDAO;

    public FollowService() {
        followDAO = new FollowDAO();
    }

    @Override
    public Boolean checkUserIsFollow(int userSender, int userReceive) {
        return followDAO.checkUserIsFollow(userSender, userReceive) != null;
    }

    @Override
    public int countUserSender(int userSender) {
        return followDAO.countUserSender(userSender);
    }

    @Override
    public int countUserReceive(int userReceive) {
        return followDAO.countUserReceive(userReceive);
    }

    @Override
    public FollowModel save(FollowModel followModel) {
        followModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Long id = followDAO.save(followModel);
        return followDAO.findOne(id);
    }

    @Override
    public void deleteByUserSenderAndUserReceive(int userSender, int userReceive) {
        followDAO.deleteByUserSenderAndUserReceive(userSender, userReceive);
    }
}
