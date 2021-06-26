package com.ldnhat.service.impl;

import com.ldnhat.DAO.ICommentDAO;
import com.ldnhat.DAO.impl.CommentDAO;
import com.ldnhat.model.CommentModel;
import com.ldnhat.service.ICommentService;

import java.util.List;

public class CommentService implements ICommentService {

    private ICommentDAO commentDAO;

    public CommentService() {
        commentDAO = new CommentDAO();
    }

    @Override
    public List<CommentModel> findByTweetId(int tweetId) {
        return commentDAO.findByTweetId(tweetId);
    }

    @Override
    public CommentModel findOne(Long id) {
        return commentDAO.findOne(id);
    }

    @Override
    public CommentModel saveComment(CommentModel commentModel) {
        Long commentId = commentDAO.save(commentModel);
        return findOne(commentId);
    }

    @Override
    public void deleteComment(Long id) {
        commentDAO.deleteComment(id);
    }

    @Override
    public int countCommentByTweetId(Long id) {
        return commentDAO.countCommentByTweetId(id);
    }
}
