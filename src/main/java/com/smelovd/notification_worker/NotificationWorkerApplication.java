package com.smelovd.notification_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NotificationWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationWorkerApplication.class, args);
    }

}
