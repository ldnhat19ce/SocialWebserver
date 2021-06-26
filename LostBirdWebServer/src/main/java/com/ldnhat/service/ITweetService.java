package com.ldnhat.service;

import com.ldnhat.model.TweetModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ITweetService {

    List<TweetModel> tweet(int userId);
    String saveFileTweet(HttpServletRequest request, HttpServletResponse response, TweetModel tweetModel)
            throws ServletException, IOException;
    TweetModel save(TweetModel tweetModel);
    TweetModel findOne(Long id);
    TweetModel update(TweetModel tweetModel);
    void updateTweetStatus(TweetModel tweetModel);
}
