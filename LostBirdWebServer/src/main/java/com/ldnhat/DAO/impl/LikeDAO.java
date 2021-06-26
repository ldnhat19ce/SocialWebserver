package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.ILikeDAO;
import com.ldnhat.mapper.impl.LikeMapper;
import com.ldnhat.model.LikeModel;

import java.sql.Date;
import java.util.List;

public class LikeDAO extends AbstractDAO<LikeModel> implements ILikeDAO {


    @Override
    public void deleteLike(Long id) {
        StringBuilder sql = new StringBuilder("DELETE FROM likes l ");
        sql.append("WHERE l.like_id = ?");

        Object[] params = {id};

        update(sql.toString(), params);
    }

    @Override
    public Long save(int userId, int tweetId, Date createDate) {
        StringBuilder sql = new StringBuilder("INSERT INTO likes");
        sql.append("(user_id, tweet_id, create_date) VALUES(?,?,?)");

        Object[] params = {userId, tweetId, createDate};
        return insert(sql.toString(), params);
    }

    @Override
    public LikeModel findLikeByTweetId(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM likes l ");
        sql.append("WHERE l.tweet_id = ?");

        Object[] params = {id};

        List<LikeModel> likeModels = query(sql.toString(), new LikeMapper(), "", params);
        return likeModels.isEmpty()? null : likeModels.get(0);
    }

    @Override
    public LikeModel findByUserIdAndTweetId(Long userId, Long tweetId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM likes l ");
        sql.append("WHERE l.user_id = ? AND l.tweet_id = ?");

        Object[] params = {userId, tweetId};

        List<LikeModel> likeModels = query(sql.toString(), new LikeMapper(), "", params);
        return likeModels.isEmpty() ? null : likeModels.get(0);
    }

    @Override
    public int countLikeByTweetId(Long id) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM likes l ");
        sql.append("WHERE l.tweet_id = ?");

        Object[] params = {id};
        return count(sql.toString(), params);
    }
}
