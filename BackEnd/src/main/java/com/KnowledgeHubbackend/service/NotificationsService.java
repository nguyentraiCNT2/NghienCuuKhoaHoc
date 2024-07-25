package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.HistoryDTO;
import com.KnowledgeHubbackend.dto.NotificationsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationsService {
    List<NotificationsDTO> getAll(Pageable pageable);
    List<NotificationsDTO> getByUserid(String userid,Pageable pageable);
    List<NotificationsDTO> getByDocumentid(Integer documentid, Pageable pageable);
    List<NotificationsDTO> getByDocumentid(Integer documentid);
    void deleteByNotificationid(Integer notificationid);
    int totalItem();
}
