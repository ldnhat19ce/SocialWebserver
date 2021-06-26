package com.ldnhat.mapper.impl;

import com.ldnhat.mapper.RowMapper;
import com.ldnhat.model.CommentModel;
import com.ldnhat.model.TweetModel;
import com.ldnhat.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<CommentModel> {
    @Override
    public CommentModel mapRow(ResultSet rs, String flag) throws SQLException {

        CommentModel commentModel = new CommentModel();

        commentModel.setId(rs.getInt("c.comment_id"));
        commentModel.setCommentStatus(rs.getString("c.comment_status"));

        UserModel userModel = new UserModel();
        userModel.setId(rs.getInt("c.comment_by"));
        commentModel.setCommentBy(userModel);

        TweetModel tweetModel = new TweetModel();
        tweetModel.setId(rs.getInt("c.comment_tweet"));
        commentModel.setCommentTweet(tweetModel);

        return commentModel;
    }
}
