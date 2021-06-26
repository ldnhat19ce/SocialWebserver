package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.Utils.HttpUtil;
import com.ldnhat.model.CommentModel;
import com.ldnhat.model.TweetModel;
import com.ldnhat.model.UserModel;
import com.ldnhat.service.ICommentService;
import com.ldnhat.service.IUserService;
import com.ldnhat.service.impl.CommentService;
import com.ldnhat.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(urlPatterns = {"/api/comments"})
public class CommentAPI extends HttpServlet {

    private ICommentService commentService;
    private IUserService userService;

    public CommentAPI() {
        userService = new UserService();
        commentService = new CommentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");
        String tweetId = request.getParameter("tweetId");

        if (message.equals("FIND_COMMENT_BY_TWEET_ID")){

            List<CommentModel> commentModels = commentService.findByTweetId(Integer.parseInt(tweetId));

            for (CommentModel commentModel : commentModels){
                UserModel userModel = userService.findOne((long) commentModel.getCommentBy().getId());
                commentModel.setCommentBy(userModel);

            }

            objectMapper.writeValue(response.getOutputStream(), commentModels);
        }else if (message.equals("COUNT_COMMENT_BY_TWEET_ID")){
            int amount = commentService.countCommentByTweetId(Long.parseLong(tweetId));

            objectMapper.writeValue(response.getOutputStream(), amount);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        CommentModel commentModel = HttpUtil.of(request.getReader()).toModel(CommentModel.class);

        if (commentModel.getMessage().equals("SAVE_COMMENT")){

            commentModel.setCreateDate(new Timestamp(System.currentTimeMillis()));

            commentModel = commentService.saveComment(commentModel);

            List<CommentModel> commentModels = commentService.findByTweetId(commentModel.getCommentTweet().getId());

            for (CommentModel c : commentModels){
                UserModel userModel = userService.findOne((long) c.getCommentBy().getId());
                c.setCommentBy(userModel);

            }
            objectMapper.writeValue(response.getOutputStream(), commentModels);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");
        if (message.equals("DELETE_COMMENT_BY_ID")){
            String commentId = request.getParameter("commentId");
            String tweetId = request.getParameter("tweetId");

            commentService.deleteComment(Long.parseLong(commentId));

            List<CommentModel> commentModels = commentService.findByTweetId(Integer.parseInt(tweetId));

            for (CommentModel commentModel : commentModels){
                UserModel userModel = userService.findOne((long) commentModel.getCommentBy().getId());
                commentModel.setCommentBy(userModel);
            }

            objectMapper.writeValue(response.getOutputStream(), commentModels);
        }
    }
}
