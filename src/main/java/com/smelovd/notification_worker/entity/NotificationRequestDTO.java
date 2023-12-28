package com.smelovd.notification_worker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDTO {

    @Id
    private String id;
    private String message;
    private NotificationRequestStatus status;
}
