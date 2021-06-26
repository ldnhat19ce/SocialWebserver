package com.ldnhat.DAO;

import com.ldnhat.model.TweetModel;

import java.util.List;

public interface ITweetDAO extends GenericDAO<TweetModel> {

    List<TweetModel> tweet(int userId);
    Long save(TweetModel tweetModel);
    TweetModel findOne(Long id);
    void update(TweetModel tweetModel);
    void updateTweetStatus(TweetModel tweetModel);
}
