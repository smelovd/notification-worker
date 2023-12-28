package com.smelovd.notification_worker.services;

import com.smelovd.notification_worker.entity.Notification;
import com.smelovd.notification_worker.entity.NotificationRequestDTO;
import com.smelovd.notification_worker.entity.NotificationStatus;
import com.smelovd.notification_worker.repositories.NotificationRequestRepository;
import com.smelovd.notification_worker.services.senders.MailSenderService;
import com.smelovd.notification_worker.services.senders.SmsSenderService;
import com.smelovd.notification_worker.services.senders.senders_metadata.SenderServiceImpl;
import com.smelovd.notification_worker.services.senders.senders_metadata.SenderServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.smelovd.notification_worker.services.senders.senders_metadata.SenderServiceType.MAIL;
import static com.smelovd.notification_worker.services.senders.senders_metadata.SenderServiceType.SMS;

@Service
@Slf4j
@CacheConfig(cacheNames = "notification_requests")
public class NotificationService {

    private final NotificationRequestRepository notificationRequestRepository;
    private final Map<SenderServiceType, SenderServiceImpl> senderServiceMap;

    @Autowired
    public NotificationService(MailSenderService mailSenderService, SmsSenderService smsSenderService, NotificationRequestRepository notificationRequestRepository) {
        this.notificationRequestRepository = notificationRequestRepository;

        this.senderServiceMap = new EnumMap<>(SenderServiceType.class);
        senderServiceMap.put(SMS, smsSenderService);
        senderServiceMap.put(MAIL, mailSenderService);
    }

    public Mono<List<Notification>> sendNotifications(List<Notification> notifications) {
        return Flux.fromIterable(notifications)
                .doOnNext(n -> n.setStatus(send(
                        n.getNotificationService(),
                        n.getServiceUserId(),
                        getMessageByRequestId(n.getNotificationId())
                ))).collectList();
    }

    @Cacheable(key = "#id", value = "messages")
    public String getMessageByRequestId(String id) {
        return notificationRequestRepository.findById(id)
                .map(NotificationRequestDTO::getMessage).block();
    }

    public NotificationStatus send(SenderServiceType service, String serviceUserId, String message) {
        var sender = senderServiceMap.get(service);
        if (sender != null) return sender.send(serviceUserId, message);
        log.error("Invalid message type " + service);
        throw new IllegalArgumentException("Invalid message type");
    }
}
