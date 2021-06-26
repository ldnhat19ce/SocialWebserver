package com.ldnhat.mapper.impl;

import com.ldnhat.mapper.RowMapper;
import com.ldnhat.model.TokenModel;
import com.ldnhat.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements RowMapper<TokenModel> {

    @Override
    public TokenModel mapRow(ResultSet rs, String flag) throws SQLException {

        TokenModel tokenModel = new TokenModel();

        tokenModel.setId(rs.getInt("tk.token_id"));
        tokenModel.setTokenKey(rs.getString("tk.token_key"));

        UserModel userModel = new UserModel();
        userModel.setId(rs.getInt("tk.user_id"));

        tokenModel.setUserModel(userModel);

        return tokenModel;
    }
}
