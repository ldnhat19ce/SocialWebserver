package com.ldnhat.service;

import com.ldnhat.model.MessageModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IMessageService {

    List<MessageModel> findByUserSenderAndUserReceiver(int messageFrom, int messageTo);
    String saveFileMessage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
