package com.smelovd.notification_api.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaSerializer implements Serializer<NotificationApi> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, NotificationApi notificationApi) {
        if (notificationApi == null) return null;

        try {
            return objectMapper.writeValueAsBytes(notificationApi);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("serialization exception", e);
        }
    }
}
