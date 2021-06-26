package com.ldnhat.DAO;

import com.ldnhat.model.TrendModel;

import java.util.List;

public interface ITrendDAO extends GenericDAO<TrendModel> {

    List<TrendModel> findAll();
    Boolean checkHashtag(String hashtag);
    void update(TrendModel trendModel);
    TrendModel findByHashtag(String hashtag);
    Long save(TrendModel trendModel);
    TrendModel findOne(Long id);
}
