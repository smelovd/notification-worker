package com.smelovd.notification_api.entity;

import com.smelovd.notification_worker.kafka.KafkaDeserializer;
import com.smelovd.notification_worker.entity.Notification;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        KafkaSerializer kafkaSerializer = new KafkaSerializer();
        KafkaDeserializer kafkaDeserializer = new KafkaDeserializer();

        NotificationApi notificationApi = new NotificationApi(1L, "1234", "124", 1L);
        System.out.println(notificationApi + " " + notificationApi.getClass());

        byte[] notificationApiBytes = kafkaSerializer.serialize("notifications", notificationApi);
        Notification notificationEnded = kafkaDeserializer.deserialize("notifications", notificationApiBytes);

        System.out.println(notificationEnded + " " + notificationEnded.getClass());
        System.out.println(LocalDateTime.now());
    }
}
