package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.INotificationDAO;
import com.ldnhat.mapper.impl.NotificationMapper;
import com.ldnhat.model.NotificationModel;

import java.util.List;

public class NotificationDAO extends AbstractDAO<NotificationModel> implements INotificationDAO {

    @Override
    public Long save(NotificationModel notificationModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO notification");
        sql.append("(notification_from, notification_for, type, target, status, create_date) ");
        sql.append("VALUES(?,?,?,?,?,?)");

        Object[] params = {notificationModel.getNotificationFrom().getId(),
                    notificationModel.getNotificationFor().getId(), notificationModel.getType(),
                    notificationModel.getTarget(), notificationModel.getStatus(), notificationModel.getCreateDate()
        };

        return insert(sql.toString(), params);
    }

    @Override
    public List<NotificationModel> findByNotificationFor(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM notification nt ");
        sql.append("WHERE nt.notification_for = ? AND nt.type != ? ");
        sql.append("ORDER BY nt.create_date DESC");
        Object[] params = {id, "mention"};

        return query(sql.toString(), new NotificationMapper(), "", params);
    }

    @Override
    public List<NotificationModel> findByNotificationForAndType(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM notification nt ");
        sql.append("WHERE nt.notification_for = ? AND nt.type = ? ORDER BY nt.create_date DESC");

        Object[] params = {id, "mention"};

        return query(sql.toString(), new NotificationMapper(), "", params);
    }
}
