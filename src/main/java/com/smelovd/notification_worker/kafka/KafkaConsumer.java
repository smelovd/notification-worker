package com.smelovd.notification_worker.kafka;

import com.smelovd.notification_worker.entity.Notification;
import com.smelovd.notification_worker.repositories.NotificationRepository;
import com.smelovd.notification_worker.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    @KafkaListener(topics = "notifications", groupId = "1", batch = "true", concurrency = "2")
    public void notificationListener(List<Notification> notifications) {
        notificationService.sendNotifications(notifications).subscribe(
                notificationRepository::saveAll
        );
        log.info("sent notification");
    }
}
