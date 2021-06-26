package com.ldnhat.mapper.impl;

import com.ldnhat.mapper.RowMapper;
import com.ldnhat.model.LikeModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeMapper implements RowMapper<LikeModel> {

    @Override
    public LikeModel mapRow(ResultSet rs, String flag) throws SQLException {

        LikeModel likeModel = new LikeModel();

        likeModel.setId(rs.getInt("l.like_id"));

        return likeModel;
    }
}
