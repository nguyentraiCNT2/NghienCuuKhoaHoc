package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.HistoryEntity;
import com.KnowledgeHubbackend.entity.NotificationsEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Integer> {
    List<NotificationsEntity> findByUserid(UsersEntity users, Pageable pageable);
    List<NotificationsEntity> findByDocumentid(DocumentEntity document, Pageable pageable);
    void deleteByNotificationid(Integer notificationid);
}
