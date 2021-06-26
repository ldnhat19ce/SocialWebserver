package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.model.MessageModel;
import com.ldnhat.service.IMessageService;
import com.ldnhat.service.ITokenNotificationService;
import com.ldnhat.service.impl.MessageService;
import com.ldnhat.service.impl.TokenNotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/messages"})
public class MessageAPI extends HttpServlet {

    private IMessageService messageService;
    private ITokenNotificationService tokenNotificationService;

    public MessageAPI() {
        messageService = new MessageService();
        tokenNotificationService = new TokenNotificationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String message = request.getParameter("message");
        String messageTo = request.getParameter("messageTo");
        String messageFrom = request.getParameter("messageFrom");

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (message.equals("RESPONSE_BY_MESSAGE_TO_AND_MESSAGE_FROM")){
            List<MessageModel> messageModels =
                    messageService.findByUserSenderAndUserReceiver(Integer.parseInt(messageFrom), Integer.parseInt(messageTo));
            objectMapper.writeValue(response.getOutputStream(), messageModels);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String message = request.getParameter("message");

        if (message.equals("UPLOAD_IMAGE_MESSAGE")){
            String filename = messageService.saveFileMessage(request, response);
            System.out.println("demo demo demooooooooooooooooooooooo");

            objectMapper.writeValue(response.getOutputStream(), filename);
        }else if (message.equals("")){

        }
    }
}
