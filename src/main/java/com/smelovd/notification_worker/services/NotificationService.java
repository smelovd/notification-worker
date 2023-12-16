package com.smelovd.notification_worker.services;

import com.smelovd.notification_worker.entity.Notification;
import com.smelovd.notification_worker.repositories.NotificationRequestRepository;
import com.smelovd.notification_worker.services.senders.DiscordSenderService;
import com.smelovd.notification_worker.services.senders.MailSenderService;
import com.smelovd.notification_worker.services.senders.PushSenderService;
import com.smelovd.notification_worker.services.senders.SmsSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final MailSenderService mailSenderService;
    private final SmsSenderService smsSenderService;
    private final DiscordSenderService discordSenderService;
    private final PushSenderService pushSenderService;

    private final NotificationRequestRepository notificationRequestRepository;

    public Mono<Notification> sendToTheRequiredService(Notification notification) {
        try {
            var message = getMessage(notification.getNotificationGroupId());
            return switch (notification.getNotificationService()) {
                case "MAIL" -> mailSenderService.send(notification, message);
                case "SMS" -> smsSenderService.send(notification, message);
                case "DISCORD" -> discordSenderService.send(notification, message);
                case "PUSH" -> pushSenderService.send(notification, message);

                default ->
                        throw new IllegalStateException("Unexpected value: " + notification.getNotificationService());
            };
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Mono.error(new Exception("not found message group"));
        }
    }

    public String getMessage(Long id) {
        return notificationRequestRepository.findMessageById(id)
                .orElseThrow();
    }
}
