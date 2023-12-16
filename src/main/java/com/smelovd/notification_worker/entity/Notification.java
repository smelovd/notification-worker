package com.smelovd.notification_worker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    private Long id;
    private String serviceUserId;
    private String notificationService;
    private Long notificationGroupId;
    private Timestamp timestamp;
    private String status;
    private Long countTry;
}
