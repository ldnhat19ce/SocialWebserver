package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.Utils.HttpUtil;
import com.ldnhat.model.TokenModel;
import com.ldnhat.service.ITokenNotificationService;
import com.ldnhat.service.impl.TokenNotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/token/notification"})
public class TokenNotificationAPI extends HttpServlet {

    private static String key = "AAAAeuo9j10:APA91bE3GHBUrwgq9beHi5bDrWAia5jUbPt1Y6iP9esZW6sB1_" +
            "HO_i4iEduRKaCqYzL5FRgOJ-fTQ9Yq4iSekG9wrLxcxNm7B2C1iwSuyXpaInY2XXz_qoHbo8kJKEdWpdEy-S32RVsg";

    private ITokenNotificationService tokenNotificationService;

    public TokenNotificationAPI() {
        tokenNotificationService = new TokenNotificationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = request.getParameter("message");

        if (message.equals("")){
            String userSender = request.getParameter("userSender");
            String type = request.getParameter("type");

            List<TokenModel> tokenModels = tokenNotificationService.findByUserId(Long.parseLong(userSender));

            for (TokenModel tokenModel : tokenModels){

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        TokenModel tokenModel = HttpUtil.of(request.getReader()).toModel(TokenModel.class);

        if (tokenModel.getMessage().equals("SAVE_TOKEN_NOTIFICATION")){
            tokenModel = tokenNotificationService.save(tokenModel);

            System.out.println("demo");
            objectMapper.writeValue(response.getOutputStream(), tokenModel);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        TokenModel tokenModel = HttpUtil.of(request.getReader()).toModel(TokenModel.class);

        if (tokenModel.getMessage().equals("UPDATE_TOKEN_NOTIFICATION_BY_ID")){
            System.out.println("message "+tokenModel.getMessage());
            tokenModel = tokenNotificationService.update(tokenModel);

            System.out.println("update token");
            objectMapper.writeValue(response.getOutputStream(), tokenModel);
        }
    }
}
