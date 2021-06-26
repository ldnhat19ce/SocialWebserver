package com.ldnhat.service.impl;

import com.ldnhat.DAO.ITrendDAO;
import com.ldnhat.DAO.impl.TrendDAO;
import com.ldnhat.model.TrendModel;
import com.ldnhat.service.ITrendService;

import java.sql.Timestamp;
import java.util.List;

public class TrendService implements ITrendService {

    private ITrendDAO trendDAO;

    public TrendService() {
        trendDAO = new TrendDAO();
    }

    @Override
    public List<TrendModel> findAll() {
        return trendDAO.findAll();
    }

    @Override
    public Boolean checkHashtag(String hashtag) {
        return trendDAO.checkHashtag(hashtag);
    }

    @Override
    public TrendModel update(TrendModel trendModel) {
        trendDAO.update(trendModel);
        return trendDAO.findOne((long) trendModel.getId());
    }

    @Override
    public TrendModel findByHashtag(String hashtag) {
        return trendDAO.findByHashtag(hashtag);
    }

    @Override
    public TrendModel save(TrendModel trendModel) {
        trendModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Long trendId = trendDAO.save(trendModel);
        return trendDAO.findOne(trendId);
    }
}
