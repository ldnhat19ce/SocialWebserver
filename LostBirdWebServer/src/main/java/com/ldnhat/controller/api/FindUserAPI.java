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
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/users/search")
public class FindUserAPI extends HttpServlet {

    private UserService userService;

    public FindUserAPI() {

        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        List<UserModel> userModels = mapper.readValue(HttpUtil.handleRequest(request.getReader())
                , new TypeReference<List<UserModel>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
        List<UserModel> users = new ArrayList<>();
        for (UserModel userModel : userModels){
            UserModel u = userService.findUserByUsername(userModel.getUsername());
            users.add(u);
            System.out.println("user_tessssss "+u.getId());
        }
        mapper.writeValue(response.getOutputStream(), users);
    }
}
