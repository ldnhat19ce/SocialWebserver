package com.ldnhat.service;

import com.ldnhat.model.TrendModel;

import java.util.List;

public interface ITrendService {

    List<TrendModel> findAll();
    Boolean checkHashtag(String hashtag);
    TrendModel update(TrendModel trendModel);
    TrendModel findByHashtag(String hashtag);
    TrendModel save(TrendModel trendModel);
}
