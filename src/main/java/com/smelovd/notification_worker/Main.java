package com.smelovd.notification_worker;

import com.smelovd.notification_api.entity.KafkaSerializer;
import com.smelovd.notification_api.entity.NotificationApi;

public class Main {
    public static void main(String[] args) {
        KafkaSerializer kafkaSerializer = new KafkaSerializer();
        KafkaDeserializer kafkaDeserializer = new KafkaDeserializer();

        NotificationApi notificationApi = new NotificationApi(1L, "1234", "124", 1L);
        System.out.println(notificationApi + " " + notificationApi.getClass());

        byte[] notificationApiBytes = kafkaSerializer.serialize("notifications", notificationApi);
        com.smelovd.notification_worker.Notification notificationEnded = kafkaDeserializer.deserialize("notifications", notificationApiBytes);

        System.out.println(notificationEnded + " " + notificationEnded.getClass());
    }
}
