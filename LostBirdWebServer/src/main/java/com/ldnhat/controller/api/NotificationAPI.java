package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.Utils.HttpUtil;
import com.ldnhat.Utils.NotificationUtils;
import com.ldnhat.model.NotificationModel;
import com.ldnhat.model.TokenModel;
import com.ldnhat.model.UserModel;
import com.ldnhat.service.INotificationService;
import com.ldnhat.service.ITokenNotificationService;
import com.ldnhat.service.IUserService;
import com.ldnhat.service.impl.NotificationService;
import com.ldnhat.service.impl.TokenNotificationService;
import com.ldnhat.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/notification"})
public class NotificationAPI extends HttpServlet {

    private INotificationService notificationService;
    private ITokenNotificationService tokenNotificationService;
    private IUserService userService;

    public NotificationAPI() {
        notificationService = new NotificationService();
        tokenNotificationService = new TokenNotificationService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String msg = request.getParameter("message");

        if (msg.equals("FIND_BY_NOTIFICATION_FOR")){
            String notificationFor = request.getParameter("notificationFor");

            List<NotificationModel> notificationModels =
                    notificationService.findByNotificationFor(Long.parseLong(notificationFor));

            handleNotification(response, objectMapper, notificationFor, notificationModels);
        }else if (msg.equals("FIND_BY_NOTIFICATION_FOR_AND_MENTION")){
            String notificationFor = request.getParameter("notificationFor");
            List<NotificationModel> notificationModels =
                    notificationService.findByNotificationForAndType(Long.parseLong(notificationFor));

            handleNotification(response, objectMapper, notificationFor, notificationModels);
        }
    }

    private void handleNotification(HttpServletResponse response, ObjectMapper objectMapper, String notificationFor, List<NotificationModel> notificationModels) throws IOException {
        for (NotificationModel notificationModel : notificationModels){

            UserModel ntFor = userService.findOne(Long.parseLong(notificationFor));
            UserModel ntFrom = userService.findOne((long) notificationModel.getNotificationFrom().getId());

            notificationModel.setNotificationFor(ntFor);
            notificationModel.setNotificationFrom(ntFrom);
        }

        objectMapper.writeValue(response.getOutputStream(), notificationModels);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        NotificationModel notificationModel = HttpUtil.of(request.getReader()).toModel(NotificationModel.class);

        if (notificationModel.getMessage().equals("SAVE_NOTIFICATION")){
            System.out.println("for "+notificationModel.getNotificationFor().getId());
            System.out.println("from "+notificationModel.getNotificationFrom().getId());
            System.out.println("status "+notificationModel.getStatus());
            System.out.println("target "+notificationModel.getTarget());
            System.out.println("type "+notificationModel.getType());
            notificationService.save(notificationModel);

            String msgNotification = "";

            List<TokenModel> tokenModels =
                    tokenNotificationService.findByUserId((long) notificationModel.getNotificationFor().getId());

            if (notificationModel.getType().equals("comment")){
                System.out.println("comment");

                for (TokenModel tokenModel : tokenModels){
                    UserModel userModel = userService.findOne((long) notificationModel.getNotificationFrom().getId());
                    msgNotification = userModel.getScreenName()+" đã bình luận về bài viết";
                    NotificationUtils.sendNotification(msgNotification, tokenModel.getTokenKey());
                }
            }else if (notificationModel.getType().equals("follow")){
                System.out.println("follow");

                for (TokenModel tokenModel : tokenModels){
                    UserModel userModel = userService.findOne((long) notificationModel.getNotificationFrom().getId());
                    msgNotification = userModel.getScreenName()+" đã follow bạn";
                    NotificationUtils.sendNotification(msgNotification, tokenModel.getTokenKey());
                }
            }else if (notificationModel.getType().equals("like")){
                System.out.println("like");

                for (TokenModel tokenModel : tokenModels){
                    UserModel userModel = userService.findOne((long) notificationModel.getNotificationFrom().getId());
                    msgNotification = userModel.getScreenName()+" đã like bài viết";
                    NotificationUtils.sendNotification(msgNotification, tokenModel.getTokenKey());
                }
            }else if (notificationModel.getType().equals("mention")){
                for (TokenModel tokenModel : tokenModels){
                    UserModel userModel = userService.findOne((long) notificationModel.getNotificationFrom().getId());
                    msgNotification = userModel.getScreenName()+" đã nhắc đến bạn";
                    NotificationUtils.sendNotification(msgNotification, tokenModel.getTokenKey());
                }
            }else if (notificationModel.getType().equals("message")){
                for (TokenModel tokenModel : tokenModels){
                    UserModel userModel = userService.findOne((long) notificationModel.getNotificationFrom().getId());
                    msgNotification = userModel.getScreenName()+" đã gửi tin nhắn";
                    NotificationUtils.sendNotification(msgNotification, tokenModel.getTokenKey());
                }
            }

            objectMapper.writeValue(response.getOutputStream(), notificationModel);
        }
    }
}
