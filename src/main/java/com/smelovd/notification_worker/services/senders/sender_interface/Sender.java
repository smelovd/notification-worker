package com.smelovd.notification_worker.services.senders.sender_interface;

import com.smelovd.notification_worker.entity.Notification;
import reactor.core.publisher.Mono;

public interface Sender {

    default Mono<Notification> send(Notification notification, String message) {
        System.out.println(notification.getId() + " " + message);
        notification.setStatus("SEND");
        return Mono.just(notification);
    }
}
