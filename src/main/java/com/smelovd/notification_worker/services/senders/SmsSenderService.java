package com.smelovd.notification_worker.services.senders;

import com.smelovd.notification_worker.entity.NotificationStatus;
import com.smelovd.notification_worker.services.senders.senders_metadata.SenderServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService implements SenderServiceImpl {

    @Override
    public NotificationStatus send(String serviceUserId, String message) {
        System.out.println("SMS \" + message + \" sent to " + serviceUserId);
        return NotificationStatus.DONE;
    }
}
