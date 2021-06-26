package com.ldnhat.service;

import com.ldnhat.model.TokenModel;

import java.util.List;

public interface ITokenNotificationService {

    TokenModel save(TokenModel tokenModel);
    TokenModel update(TokenModel tokenModel);
    List<TokenModel> findByUserId(Long id);
    TokenModel findOne(Long id);
}
