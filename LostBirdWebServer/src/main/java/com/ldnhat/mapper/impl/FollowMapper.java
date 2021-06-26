package com.ldnhat.mapper.impl;

import com.ldnhat.mapper.RowMapper;
import com.ldnhat.model.FollowModel;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowMapper implements RowMapper<FollowModel> {

    @Override
    public FollowModel mapRow(ResultSet rs, String flag) throws SQLException {
        FollowModel followModel = new FollowModel();

        followModel.setUserSender(rs.getInt("f.user_sender"));
        followModel.setUserReceive(rs.getInt("f.user_receive"));

        return followModel;
    }
}
