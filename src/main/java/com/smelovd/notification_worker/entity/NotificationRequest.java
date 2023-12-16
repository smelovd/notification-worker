package com.smelovd.notification_worker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "notification_requests")
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    @Id
    private Long id;
    private String message;
    private String filename;
    @Lob
    private byte[] file;

}
