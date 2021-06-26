package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.model.LikeModel;
import com.ldnhat.model.TweetModel;
import com.ldnhat.service.ILikeService;
import com.ldnhat.service.ITweetService;
import com.ldnhat.service.impl.LikeService;
import com.ldnhat.service.impl.TweetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(urlPatterns = {"/api/likes"})
public class LikeAPI extends HttpServlet {

    private ILikeService likeService;
    private ITweetService tweetService;

    public LikeAPI() {
        likeService = new LikeService();
        tweetService = new TweetService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String message = request.getParameter("message");
        String tweetId = request.getParameter("tweetId");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        if (message.equals("FIND_BY_TWEET_ID_AND_USER_ID")){
            LikeModel likeModel = likeService.findByTweetId(Long.parseLong(tweetId));

            objectMapper.writeValue(response.getOutputStream(), likeModel);
        }else if (message.equals("COUNT_LIKE_BY_TWEET_ID")){
            int likeAmount = likeService.countLikeByTweetId(Long.parseLong(tweetId));
            objectMapper.writeValue(response.getOutputStream(), likeAmount);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String message = request.getParameter("message");
        String userId = request.getParameter("userId");
        String tweetId = request.getParameter("tweetId");

        if (message.equals("SAVE_LIKE_BY_USERID_AND_TWEETID")){
            System.out.println("tweet id "+tweetId);
            TweetModel tweetModel = tweetService.findOne(Long.parseLong(tweetId));
            System.out.println("model "+tweetModel.getId());
            tweetModel.setIsLike(1);
            tweetService.update(tweetModel);
            likeService.save(Integer.parseInt(userId), Integer.parseInt(tweetId), new Date(System.currentTimeMillis()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String message = request.getParameter("message");
        String tweetId = request.getParameter("tweetId");

        if(message.equals("DELETE_LIKE_BY_TWEET_ID")){
            LikeModel likeModel = likeService.findByTweetId(Long.parseLong(tweetId));
            likeService.deleteLike((long) likeModel.getId());
            TweetModel tweetModel = tweetService.findOne(Long.parseLong(tweetId));
            tweetModel.setIsLike(0);
            tweetService.update(tweetModel);
        }

    }
}
