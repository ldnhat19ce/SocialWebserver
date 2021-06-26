package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.IFollowDAO;
import com.ldnhat.mapper.impl.FollowMapper;
import com.ldnhat.model.FollowModel;

import java.util.List;

public class FollowDAO extends AbstractDAO<FollowModel> implements IFollowDAO {

    @Override
    public FollowModel checkUserIsFollow(int userSender, int userReceive) {

        StringBuilder sql = new StringBuilder("SELECT * FROM follow f ");
        sql.append("WHERE user_sender = ? AND user_receive = ?");

        Object[] params = {userSender, userReceive};

        List<FollowModel> followModels = query(sql.toString(), new FollowMapper(), "", params);
        return followModels.isEmpty() ? null : followModels.get(0);
    }

    @Override
    public int countUserSender(int userSender) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM follow f ");
        sql.append("WHERE f.user_sender = ?");

        Object[] params = {userSender};
        return count(sql.toString(), params);
    }

    @Override
    public int countUserReceive(int userReceive) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM follow f ");
        sql.append("WHERE f.user_receive = ?");

        Object[] params = {userReceive};
        return count(sql.toString(), params);
    }

    @Override
    public Long save(FollowModel followModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO follow");
        sql.append("(user_sender, user_receive, create_date) ");
        sql.append("VALUES(?,?,?)");

        Object[] params = {followModel.getUserSender(), followModel.getUserReceive(),
                        followModel.getCreateDate()};

        return insert(sql.toString(), params);
    }

    @Override
    public void deleteByUserSenderAndUserReceive(int userSender, int userReceive) {
        StringBuilder sql = new StringBuilder("DELETE FROM follow f ");
        sql.append("WHERE f.user_sender = ? AND f.user_receive = ?");

        Object[] params = {userSender, userReceive};

        update(sql.toString(), params);
    }

    @Override
    public FollowModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM follow f ");
        sql.append("WHERE f.follow_id = ?");

        Object[] params = {id};

        List<FollowModel> followModels = query(sql.toString(), new FollowMapper(), "", params);
        return followModels.isEmpty() ? null : followModels.get(0);
    }
}
