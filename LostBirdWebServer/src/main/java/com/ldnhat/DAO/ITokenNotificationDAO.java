package com.ldnhat.DAO;

import com.ldnhat.model.TokenModel;

import java.util.List;

public interface ITokenNotificationDAO extends GenericDAO<TokenModel> {

    List<TokenModel> findByUserId(Long id);
    Long save(TokenModel tokenModel);
    void update(TokenModel tokenModel);
    TokenModel findOne(Long id);
}
