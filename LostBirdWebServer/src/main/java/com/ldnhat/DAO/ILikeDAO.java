package com.ldnhat.DAO;

import com.ldnhat.model.LikeModel;

import java.sql.Date;

public interface ILikeDAO extends GenericDAO<LikeModel> {

    void deleteLike(Long id);

    Long save(int userId, int tweetId, Date createDate);

    LikeModel findLikeByTweetId(Long id);
    LikeModel findByUserIdAndTweetId(Long userId, Long tweetId);
    int countLikeByTweetId(Long id);
}
