package com.smelovd.notification_worker.repositories;

import com.smelovd.notification_worker.entity.NotificationRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRequestRepository extends JpaRepository<NotificationRequest,Long> {

    @Cacheable(value = "notificationGroup", key = "#id")
    @Query(nativeQuery = true,
            value = "SELECT message FROM notification_requests WHERE id = :id")
    Optional<String> findMessageById(Long id);
}
