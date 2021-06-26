package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.Utils.HttpUtil;
import com.ldnhat.model.LikeModel;
import com.ldnhat.model.TweetModel;
import com.ldnhat.model.UserModel;
import com.ldnhat.service.ILikeService;
import com.ldnhat.service.ITweetService;
import com.ldnhat.service.IUserService;
import com.ldnhat.service.impl.LikeService;
import com.ldnhat.service.impl.TweetService;
import com.ldnhat.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(urlPatterns = {"/api/tweets"})
public class TweetAPI extends HttpServlet {

    private final ITweetService tweetService;
    private final IUserService userService;
    private final ILikeService likeService;

    public TweetAPI() {
        tweetService = new TweetService();
        userService = new UserService();
        likeService = new LikeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String message = request.getParameter("message");
        String userId = request.getParameter("userId");

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println(userId);

        if (message != null){
            if(message.equals("RESPONSE_TWEET_BY_USER_ID")){
                List<TweetModel> tweetModels = tweetService.tweet(Integer.parseInt(userId));

                for (TweetModel tweetModel : tweetModels) {
                    UserModel userModel = userService.findOne((long) tweetModel.getUserModel().getId());

                    LikeModel likeModel =
                            likeService.findByUserIdAndTweetId(
                                    Long.parseLong(userId), (long) tweetModel.getId());

                    if (likeModel != null){
                        tweetModel.setIsLike(1);
                    }else{
                        tweetModel.setIsLike(0);
                    }

                    tweetModel.setUserModel(userModel);
                }
                objectMapper.writeValue(response.getOutputStream(), tweetModels);
                System.out.println("success");
            }else if (message.equals("RESPONSE_FIND_ONE_TWEET")){
                String tweetId = request.getParameter("tweetId");
                TweetModel tweetModel = tweetService.findOne(Long.parseLong(tweetId));
                UserModel userModel = userService.findOne((long) tweetModel.getUserModel().getId());
                tweetModel.setUserModel(userModel);

                objectMapper.writeValue(response.getOutputStream(), tweetModel);
            }
        } else{
            String tweetId = request.getParameter("tweetId");
            TweetModel tweetModel = tweetService.findOne(Long.parseLong(tweetId));
//            UserModel userModel = userService.findOne((long) tweetModel.getUserModel().getId());
//            tweetModel.setUserModel(userModel);

            String tweetJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tweetModel);

            PrintWriter out = response.getWriter();
            out.println(tweetJson);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String message = request.getParameter("message");
        System.out.println(message);

        if(message.equals("SAVE_TWEET")){
            TweetModel tweetModel = HttpUtil.of(request.getReader()).toModel(TweetModel.class);
            System.out.println("user id: "+tweetModel.getUserModel().getId());
            System.out.println("image: "+tweetModel.getTweetImage());
            System.out.println("status: "+tweetModel.getTweetStatus());
            System.out.println("no file");
            tweetModel.setTweetImage("");
            tweetModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
            tweetModel = tweetService.save(tweetModel);
            objectMapper.writeValue(response.getOutputStream(), tweetModel);
        }else{
            System.out.println("file");

            String tweetId = request.getParameter("tweetId");
            TweetModel tweetModel = tweetService.findOne(Long.parseLong(tweetId));

            System.out.println("tweetid :"+tweetId);
            tweetService.saveFileTweet(request, response, tweetModel);
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        TweetModel tweetModel = HttpUtil.of(request.getReader()).toModel(TweetModel.class);

        if (tweetModel.getMessage().equals("UPDATE_TWEET_STATUS")){
            tweetService.update(tweetModel);
        }
    }
}
