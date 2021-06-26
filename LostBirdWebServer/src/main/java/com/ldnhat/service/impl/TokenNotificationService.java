package com.ldnhat.service.impl;

import com.ldnhat.DAO.ITokenNotificationDAO;
import com.ldnhat.DAO.impl.TokenNotificationDAO;
import com.ldnhat.model.TokenModel;
import com.ldnhat.service.ITokenNotificationService;

import java.sql.Timestamp;
import java.util.List;

public class TokenNotificationService implements ITokenNotificationService {

    private ITokenNotificationDAO tokenNotificationDAO;

    public TokenNotificationService() {
        tokenNotificationDAO = new TokenNotificationDAO();
    }

    @Override
    public TokenModel save(TokenModel tokenModel) {
        tokenModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Long id = tokenNotificationDAO.save(tokenModel);

        return findOne(id);
    }

    @Override
    public TokenModel update(TokenModel tokenModel) {
        tokenNotificationDAO.update(tokenModel);
        return findOne((long) tokenModel.getId());
    }

    @Override
    public List<TokenModel> findByUserId(Long id) {
        return tokenNotificationDAO.findByUserId(id);
    }

    @Override
    public TokenModel findOne(Long id) {
        return tokenNotificationDAO.findOne(id);
    }
}
