package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.StringValue;
import com.ldnhat.Utils.HttpUtil;
import com.ldnhat.model.FollowModel;
import com.ldnhat.service.IFollowService;
import com.ldnhat.service.impl.FollowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/follow"})
public class FollowAPI extends HttpServlet {

    private IFollowService followService;

    public FollowAPI() {
        followService = new FollowService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");

        if (message.equals("CHECK_USER_IS_FOLLOW")){
            String userSender = request.getParameter("userSender");
            String userReceive = request.getParameter("userReceive");

            if (!followService.checkUserIsFollow(Integer.parseInt(userSender),
                    Integer.parseInt(userReceive))){
                message = "USER_NOT_FOLLOW";
            }else{
                message = "USER_IS_FOLLOW";
            }
            objectMapper.writeValue(response.getOutputStream(), message);
        }else if (message.equals("GET_USER_SENDER")){
            String userSender = request.getParameter("userSender");

            int count = followService.countUserSender(Integer.parseInt(userSender));

            objectMapper.writeValue(response.getOutputStream(), String.valueOf(count));
        }else if (message.equals("GET_USER_RECEIVE")){
            String userReceive = request.getParameter("userReceive");

            int count = followService.countUserReceive(Integer.parseInt(userReceive));

            objectMapper.writeValue(response.getOutputStream(), String.valueOf(count));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        FollowModel followModel = HttpUtil.of(request.getReader()).toModel(FollowModel.class);

        if (followModel.getMessage().equals("SAVE_FOLLOW")){
            followModel = followService.save(followModel);

            objectMapper.writeValue(response.getOutputStream(), followModel);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        FollowModel followModel = HttpUtil.of(request.getReader()).toModel(FollowModel.class);

        if (followModel.getMessage().equals("DELETE_BY_USER_SENDER_AND_USER_RECEIVE")){

            followService.deleteByUserSenderAndUserReceive(
                    followModel.getUserSender(), followModel.getUserReceive());

        }
    }
}
