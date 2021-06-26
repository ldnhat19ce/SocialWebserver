package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.ITweetDAO;
import com.ldnhat.mapper.impl.TweetMapper;
import com.ldnhat.model.TweetModel;

import java.util.List;

public class TweetDAO extends AbstractDAO<TweetModel> implements ITweetDAO{
    @Override
    public List<TweetModel> tweet(int userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM tweets t ");
        sql.append("WHERE (t.user_id = ? ");
        sql.append("OR t.user_id IN (SELECT f.user_receive FROM follow f WHERE f.user_sender = ?)) ");
        sql.append("AND t.status = 1 ");
        sql.append("ORDER BY t.retweet_count DESC");

        Object[] params = {userId, userId};
        return query(sql.toString(), new TweetMapper(), "LIKE_", params);
    }

    @Override
    public Long save(TweetModel tweetModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO tweets");
        sql.append("(tweet_status, user_id, tweet_image, retweet_count, create_date, isLike, status) ");
        sql.append("VALUES(?,?,?,?,?,?,?)");

        Object[] params = {tweetModel.getTweetStatus(), tweetModel.getUserModel().getId(),
                tweetModel.getTweetImage(), 0, tweetModel.getCreateDate(), 0, tweetModel.getStatus()};
        return insert(sql.toString(), params);
    }

    @Override
    public TweetModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM tweets t ");
        //sql.append("INNER JOIN like l ON t.tweet_id = l.tweet_id ");
        sql.append("WHERE t.tweet_id = ?");

        Object[] params = {id};

        List<TweetModel> tweetModels = query(sql.toString(), new TweetMapper(), "LIKE_N", params);
        return tweetModels.isEmpty() ? null : tweetModels.get(0);
    }

    @Override
    public void update(TweetModel tweetModel) {
        try {
            StringBuilder sql = new StringBuilder("UPDATE tweets t SET ");
            sql.append("t.tweet_status = ?, t.user_id = ?, t.tweet_image = ?, t.isLike = ?, t.status = ? ");
            sql.append("WHERE t.tweet_id = ?");

            Object[] params = {tweetModel.getTweetStatus(), tweetModel.getUserModel().getId(),
                    tweetModel.getTweetImage(),tweetModel.getIsLike(),
                    tweetModel.getStatus(), tweetModel.getId()};

            update(sql.toString(), params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateTweetStatus(TweetModel tweetModel) {
        StringBuilder sql = new StringBuilder("UPDATE tweets t SET ");
        sql.append("t.status = ? WHERE t.tweet_id = ?");

        Object[] params = {tweetModel.getStatus(), tweetModel.getId()};

        update(sql.toString(), params);
    }
}
