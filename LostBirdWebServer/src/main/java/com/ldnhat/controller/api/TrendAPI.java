package com.ldnhat.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.model.TrendModel;
import com.ldnhat.service.ITrendService;
import com.ldnhat.service.impl.TrendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/trends"})
public class TrendAPI extends HttpServlet {

    private ITrendService trendService;

    public TrendAPI() {
        trendService = new TrendService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");
        if (message.equals("FIND_ALL_HASHTAG")){
            List<TrendModel> trendModels = trendService.findAll();

            objectMapper.writeValue(response.getOutputStream(), trendModels);
        }else if (message.equals("CHECK_HASHTAG")){
            String hashtag = request.getParameter("hashtag");

            if (trendService.checkHashtag(hashtag)){
                message = "HASH_TAG_IS_EXIST";
            }else{
                message = "HASH_TAG_IS_EMPTY";
            }
            objectMapper.writeValue(response.getOutputStream(), message);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");
        if (message.equals("SAVE_HASHTAG")){
            String hashtag = request.getParameter("hashtag");
            TrendModel trendModel = new TrendModel();
            trendModel.setAmount(1);
            trendModel.setHashtag(hashtag);

            trendModel = trendService.save(trendModel);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");
        if (message.equals("UPDATE_AMOUNT_HASHTAG")){
            String hashtag = request.getParameter("hashtag");
            TrendModel trendModel = trendService.findByHashtag(hashtag);

            trendModel = trendService.update(trendModel);
        }
    }
}
