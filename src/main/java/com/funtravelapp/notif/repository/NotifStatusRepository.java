package com.funtravelapp.notif.repository;

import com.funtravelapp.notif.model.notifStatus.NotifStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotifStatusRepository extends JpaRepository<NotifStatus, Integer> {
    Optional<NotifStatus> findByTransactionId(Integer transactionId);
}
