package com.ldnhat.DAO;

import com.ldnhat.model.NotificationModel;

import java.util.List;

public interface INotificationDAO extends GenericDAO<NotificationModel> {

    Long save(NotificationModel notificationModel);
    List<NotificationModel> findByNotificationFor(Long id);
    List<NotificationModel> findByNotificationForAndType(Long id);
}
