package com.ldnhat.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.Utils.HttpUtil;
import com.ldnhat.model.UserModel;
import com.ldnhat.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/users"})
public class UserAPI  extends HttpServlet {

    private UserService userService;

    public UserAPI() {

        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");

        if (message != null){
            if(message.equals("FIND_ONE_USER")){
                String userId = request.getParameter("userId");
                UserModel userModel = userService.findOne(Long.parseLong(userId));
                objectMapper.writeValue(response.getOutputStream(), userModel);
            }else if(message.equals("FIND_USER_IS_FOLLOWED")){
                String userId = request.getParameter("userId");

                List<UserModel> userModels = userService.findUserNotFollow(Integer.parseInt(userId));

                objectMapper.writeValue(response.getOutputStream(), userModels);
            } else if (message.equals("FIND_ALL_USER")){
                List<UserModel> users = userService.findAll();
//            String userJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
//
//            PrintWriter out = response.getWriter();
//            out.println(userJson);
//            out.flush();
                objectMapper.writeValue(response.getOutputStream(), users);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");


        UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);
        if(userModel.getMessage().equals("REGISTER")){
            if(userService.findByUsernameAndEmail(userModel.getUsername(),
                    userModel.getEmail()) == null){

                System.out.println(userModel.getEmail());
                userModel = userService.save(userModel);
                userModel.setMessage("REGISTER_ACCOUNT_SUCCESS");
                objectMapper.writeValue(response.getOutputStream(), userModel);
            }else{
                userModel.setMessage("REGISTER_NAME_OR_EMAIL_EXIS");
                objectMapper.writeValue(response.getOutputStream(), userModel);
            }
        }else if(userModel.getMessage().equals("CLIENT_REQUIRED_AUTHORIZATION")){
            if (userService.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword()) != null){
                System.out.println(userModel.getEmail());
                userModel = userService.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
                userModel.setMessage("AUTHORIZATION_SUCCESSFUL");
                objectMapper.writeValue(response.getOutputStream(), userModel);
            }else{
                userModel.setMessage("AUTHORIZATION_FAILED");
                System.out.println(userModel.getEmail());
                objectMapper.writeValue(response.getOutputStream(), userModel);
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String message = request.getParameter("message");
        if (message != null){
            if (message.equals("UPDATE_PROFILE_COVER")){
                String filename = "http://192.168.1.6:8080/LostBirdWebServer_war_exploded/image_user/"
                        +userService.saveProfileCoverUser(request, response);
                System.out.println("profile cover filename "+filename);
                objectMapper.writeValue(response.getOutputStream(), filename);
            }else if (message.equals("UPDATE_PROFILE_IMAGE")){
                String filename = "http://192.168.1.6:8080/LostBirdWebServer_war_exploded/image_user/"
                        + userService.saveProfileImageUser(request, response);
                System.out.println("profile image filename "+filename);

                objectMapper.writeValue(response.getOutputStream(), filename);
            }
        }else{
            UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);

            if (userModel.getMessage().equals("UPDATE_USER_UID")){
                userService.updateUserUid(userModel);
            }else if (userModel.getMessage().equals("UPDATE_USER")){
                System.out.println("user_profile_image "+userModel.getProfileImage());
                System.out.println("user_profile_cover "+userModel.getProfileCover());
                userModel = userService.updateUser(userModel);
                System.out.println("update user");

                objectMapper.writeValue(response.getOutputStream(), userModel);
            }
        }

    }
}
