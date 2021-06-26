package com.ldnhat.service;

import com.ldnhat.model.NotificationModel;

import java.util.List;

public interface INotificationService {

    NotificationModel save(NotificationModel notificationModel);
    List<NotificationModel> findByNotificationFor(Long id);
    List<NotificationModel> findByNotificationForAndType(Long id);
}
