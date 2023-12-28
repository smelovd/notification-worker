package com.smelovd.notification_api.entity;

import com.smelovd.notification_worker.kafka.KafkaDeserializer;
import com.smelovd.notification_worker.entity.Notification;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        KafkaSerializer kafkaSerializer = new KafkaSerializer();
        KafkaDeserializer kafkaDeserializer = new KafkaDeserializer();

        NotificationApi notificationApi = NotificationApi.builder()
                .notificationId("1")
                .notificationService("SMS")
                .build();
        System.out.println(notificationApi + " " + notificationApi.getClass());

        byte[] notificationApiBytes = kafkaSerializer.serialize("notifications", notificationApi);
        Notification notificationEnded = kafkaDeserializer.deserialize("notifications", notificationApiBytes);
        //System.out.println(notificationEnded.getNotificationService().send("1", "1"));
        System.out.println(notificationEnded + " " + notificationEnded.getClass());
        System.out.println(LocalDateTime.now());
    }
}
