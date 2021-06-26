package com.ldnhat.service.impl;

import com.ldnhat.DAO.INotificationDAO;
import com.ldnhat.DAO.impl.NotificationDAO;
import com.ldnhat.model.NotificationModel;
import com.ldnhat.service.INotificationService;

import java.sql.Timestamp;
import java.util.List;

public class NotificationService implements INotificationService {

    private INotificationDAO notificationDAO;

    public NotificationService() {
        notificationDAO = new NotificationDAO();
    }

    @Override
    public NotificationModel save(NotificationModel notificationModel) {
        notificationModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Long id = notificationDAO.save(notificationModel);
        return null;
    }

    @Override
    public List<NotificationModel> findByNotificationFor(Long id) {
        return notificationDAO.findByNotificationFor(id);
    }

    @Override
    public List<NotificationModel> findByNotificationForAndType(Long id) {
        return notificationDAO.findByNotificationForAndType(id);
    }
}
