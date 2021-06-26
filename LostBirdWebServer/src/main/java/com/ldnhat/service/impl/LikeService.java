package com.ldnhat.service.impl;

import com.ldnhat.DAO.ILikeDAO;
import com.ldnhat.DAO.impl.LikeDAO;
import com.ldnhat.model.LikeModel;
import com.ldnhat.service.ILikeService;

import java.sql.Date;
import java.sql.Timestamp;

public class LikeService implements ILikeService {

    private ILikeDAO likeDAO;

    public LikeService() {
        likeDAO = new LikeDAO();
    }

    @Override
    public void deleteLike(Long id) {
        likeDAO.deleteLike(id);
    }

    @Override
    public Long save(int userId, int tweetId, Date createDate) {
        return likeDAO.save(userId, tweetId, createDate);
    }

    @Override
    public LikeModel findByTweetId(Long id) {
        return likeDAO.findLikeByTweetId(id);
    }

    @Override
    public LikeModel findByUserIdAndTweetId(Long userId, Long tweetId) {
        return likeDAO.findByUserIdAndTweetId(userId, tweetId);
    }

    @Override
    public int countLikeByTweetId(Long id) {
        return likeDAO.countLikeByTweetId(id);
    }
}
