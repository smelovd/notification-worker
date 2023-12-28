package com.smelovd.notification_worker.repositories;

import com.smelovd.notification_worker.entity.NotificationRequestDTO;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface NotificationRequestRepository extends ReactiveMongoRepository<NotificationRequestDTO, String> {
}
