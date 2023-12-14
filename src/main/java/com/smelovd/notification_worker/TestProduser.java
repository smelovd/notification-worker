package com.smelovd.notification_worker;

import com.smelovd.notification_api.entity.NotificationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestProduser {

    private final KafkaTemplate<String, NotificationApi> kafkaTemplate;

    @Bean
    public void addEvents() {
        for (int i = 0; i <10000; i++) {
            kafkaTemplate.send("notifications",new NotificationApi((long) i, "1234", "saudo", 1L));

        }
        System.out.println("added");
    }
}
