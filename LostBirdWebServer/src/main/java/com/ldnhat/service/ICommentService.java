package com.ldnhat.service;

import com.ldnhat.model.CommentModel;

import java.util.List;

public interface ICommentService {

    List<CommentModel> findByTweetId(int tweetId);
    CommentModel findOne(Long id);
    CommentModel saveComment(CommentModel commentModel);
    void deleteComment(Long id);
    int countCommentByTweetId(Long id);
}
