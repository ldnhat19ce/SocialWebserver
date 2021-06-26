package com.ldnhat.mapper.impl;

import com.ldnhat.mapper.RowMapper;
import com.ldnhat.model.NotificationModel;
import com.ldnhat.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationMapper implements RowMapper<NotificationModel> {

    @Override
    public NotificationModel mapRow(ResultSet rs, String flag) throws SQLException {

        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setId(rs.getInt("nt.notification_id"));

        UserModel notificationFrom = new UserModel();
        notificationFrom.setId(rs.getInt("nt.notification_from"));
        notificationModel.setNotificationFrom(notificationFrom);

        UserModel notificationFor = new UserModel();
        notificationFor.setId(rs.getInt("nt.notification_for"));
        notificationModel.setNotificationFor(notificationFor);

        notificationModel.setType(rs.getString("nt.type"));
        notificationModel.setTarget(rs.getInt("nt.target"));
        notificationModel.setStatus(rs.getInt("nt.status"));
        notificationModel.setCreateDate(rs.getTimestamp("nt.create_date"));

        return notificationModel;
    }
}
