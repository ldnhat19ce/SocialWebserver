package com.ldnhat.mapper.impl;

import com.ldnhat.mapper.RowMapper;
import com.ldnhat.model.LikeModel;
import com.ldnhat.model.TweetModel;
import com.ldnhat.model.UserModel;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TweetMapper implements RowMapper<TweetModel> {


    @Override
    public TweetModel mapRow(ResultSet rs, String flag) throws SQLException {
        TweetModel tweetModel = new TweetModel();
        tweetModel.setId(rs.getInt("t.tweet_id"));
        tweetModel.setTweetStatus(rs.getString("t.tweet_status"));
        tweetModel.setTweetImage(rs.getString("t.tweet_image"));
        tweetModel.setCreateDate(rs.getTimestamp("t.create_date"));
        tweetModel.setIsLike(rs.getInt("t.isLike"));
        tweetModel.setStatus(rs.getInt("t.status"));
        UserModel userModel = new UserModel();
        userModel.setId(rs.getInt("t.user_id"));

        tweetModel.setUserModel(userModel);

        return tweetModel;
    }
}
