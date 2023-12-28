package com.smelovd.notification_worker.services.senders.senders_metadata;

import com.smelovd.notification_worker.entity.NotificationStatus;


public interface SenderServiceImpl {
    NotificationStatus send(String serviceUserId, String message);
}
