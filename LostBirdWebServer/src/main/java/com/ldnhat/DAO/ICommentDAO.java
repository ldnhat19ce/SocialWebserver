package com.ldnhat.DAO;

import com.ldnhat.model.CommentModel;

import java.util.List;

public interface ICommentDAO extends GenericDAO<CommentModel>{

    List<CommentModel> findByTweetId(int tweetId);
    Long save(CommentModel commentModel);
    CommentModel findOne(Long id);
    void deleteComment(Long id);
    int countCommentByTweetId(Long id);
}
