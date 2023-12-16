package com.smelovd.notification_worker.kafka;

import com.smelovd.notification_api.entity.NotificationApi;
import com.smelovd.notification_worker.entity.Notification;
import com.smelovd.notification_worker.repositories.NotificationRepository;
import com.smelovd.notification_worker.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {


    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "notifications", groupId = "1", batch = "true", concurrency = "4")
    public void notificationListener(List<Notification> notifications) {
        Flux.fromIterable(notifications)
                .flatMap(notificationService::sendToTheRequiredService)
                .retry(5).onErrorComplete()
                .collectList()
                        .subscribe(
                                notificationRepository::saveAll,
                                null,
                                null
                        );
        System.out.println(LocalDateTime.now());
    }

    //Test
    @Autowired
    private KafkaTemplate<String, NotificationApi> kafkaTemplate;

    @Bean
    public int repository() {
        notificationRepository.save(new Notification(1L, "1", "1", 1L, null, null, null));
        return 1;
    }

    @Bean
    public void test() {
        for (int i = 0; i < 10001; i++) {
            kafkaTemplate.send("notifications", new NotificationApi((long) i, i + "-id", "MAIL", 1L));
        }
        System.out.println("added");
    }
}
