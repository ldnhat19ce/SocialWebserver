package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.ICommentDAO;
import com.ldnhat.mapper.impl.CommentMapper;
import com.ldnhat.model.CommentModel;

import java.util.List;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO {

    @Override
    public List<CommentModel> findByTweetId(int tweetId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM comment c ");
        sql.append("WHERE c.comment_tweet = ? ORDER BY c.create_date DESC");

        Object[] params = {tweetId};
        return query(sql.toString(), new CommentMapper(), "", params);
    }

    @Override
    public Long save(CommentModel commentModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO comment");
        sql.append("(comment_status, comment_by, comment_tweet, create_date) ");
        sql.append("VALUES(?,?,?,?)");

        Object[] params = {commentModel.getCommentStatus(), commentModel.getCommentBy().getId(),
            commentModel.getCommentTweet().getId(), commentModel.getCreateDate()
        };
        return insert(sql.toString(), params);
    }

    @Override
    public CommentModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM comment c ");
        sql.append("WHERE c.comment_id = ?");

        Object[] params = {id};

        List<CommentModel> commentModels = query(sql.toString(), new CommentMapper(), "", params);
        return commentModels.isEmpty() ? null : commentModels.get(0);
    }

    @Override
    public void deleteComment(Long id) {
        StringBuilder sql = new StringBuilder("DELETE FROM comment c ");
        sql.append("WHERE c.comment_id = ?");

        Object[] params = {id};

        update(sql.toString(), params);
    }

    @Override
    public int countCommentByTweetId(Long id) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM comment c ");
        sql.append("WHERE c.comment_tweet = ?");

        Object[] params = {id};
        return count(sql.toString(), params);
    }
}
