package com.ldnhat.mapper.impl;

import com.ldnhat.mapper.RowMapper;
import com.ldnhat.model.TrendModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrendMapper implements RowMapper<TrendModel> {

    @Override
    public TrendModel mapRow(ResultSet rs, String flag) throws SQLException {

        TrendModel trendModel = new TrendModel();

        trendModel.setId(rs.getInt("t.trend_id"));
        trendModel.setHashtag(rs.getString("t.hashtag"));
        trendModel.setAmount(rs.getInt("t.amount"));
        trendModel.setCreateDate(rs.getTimestamp("t.create_date"));

        return trendModel;
    }
}
