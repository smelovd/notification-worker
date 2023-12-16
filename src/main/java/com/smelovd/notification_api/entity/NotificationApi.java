package com.smelovd.notification_api.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationApi {


    @Id
    private Long id;

    private String serviceUserId;

    private String notificationService;

    private Long notificationGroupId;

    /*@Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "count_try")
    private Long countTry;*/
}
