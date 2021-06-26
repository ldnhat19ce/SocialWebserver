package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.ITokenNotificationDAO;
import com.ldnhat.mapper.impl.TokenMapper;
import com.ldnhat.model.TokenModel;

import java.util.List;

public class TokenNotificationDAO extends AbstractDAO<TokenModel> implements ITokenNotificationDAO {

    @Override
    public List<TokenModel> findByUserId(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM token_notification tk ");
        sql.append("WHERE tk.user_id = ?");

        Object[] params = {id};

        return query(sql.toString(), new TokenMapper(), "", params);
    }

    @Override
    public Long save(TokenModel tokenModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO token_notification");
        sql.append("(token_key, create_date) ");
        sql.append("VALUES(?,?)");

        Object[] params = {tokenModel.getTokenKey(), tokenModel.getCreateDate()};
        return insert(sql.toString(), params);
    }

    @Override
    public void update(TokenModel tokenModel) {
        StringBuilder sql = new StringBuilder("UPDATE token_notification tk ");
        sql.append("SET tk.user_id = ? WHERE tk.token_id = ?");

        Object[] params = {tokenModel.getUserModel().getId(), tokenModel.getId()};

        update(sql.toString(), params);
    }

    @Override
    public TokenModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM token_notification tk ");
        sql.append("WHERE tk.token_id = ?");

        Object[] params = {id};

        List<TokenModel> tokenModels = query(sql.toString(), new TokenMapper(), "", params);
        return tokenModels.isEmpty() ? null : tokenModels.get(0);
    }
}
