package com.smelovd.notification_worker;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @KafkaListener(topics = "notifications", groupId = "1", batch = true)
    public void notificationListener(Notification notification) {
        System.out.println(notification);
    }
}
