package com.ldnhat.service;

import com.ldnhat.model.LikeModel;

import java.sql.Date;

public interface ILikeService {

    void deleteLike(Long id);
    Long save(int userId, int tweetId, Date createDate);
    LikeModel findByTweetId(Long id);
    LikeModel findByUserIdAndTweetId(Long userId, Long tweetId);
    int countLikeByTweetId(Long id);
}
