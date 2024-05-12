package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.HistoryDTO;
import com.KnowledgeHubbackend.dto.NotificationsDTO;
import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.HistoryEntity;
import com.KnowledgeHubbackend.entity.NotificationsEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.DocumentRepository;
import com.KnowledgeHubbackend.repository.NotificationsRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.service.NotificationsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationsServiceIMPL implements NotificationsService {
    @Autowired
    private final NotificationsRepository notificationsRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final DocumentRepository documentRepository;

    public NotificationsServiceIMPL(NotificationsRepository notificationsRepository, ModelMapper modelMapper, UserRepository userRepository, DocumentRepository documentRepository) {
        this.notificationsRepository = notificationsRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<NotificationsDTO> getAll(Pageable pageable) {
        List<NotificationsDTO> results = new ArrayList<>();
        List<NotificationsEntity> notificationsEntities = notificationsRepository.findAll(pageable).getContent();
        for (NotificationsEntity item : notificationsEntities
        ) {
            NotificationsDTO dto = modelMapper.map(item, NotificationsDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<NotificationsDTO> getByUserid(String userid, Pageable pageable) {
        List<NotificationsDTO> results = new ArrayList<>();
        UsersEntity users = userRepository.findById(userid).orElse(null);
        List<NotificationsEntity> notificationsEntities = notificationsRepository.findByUserid(users,pageable);
        for (NotificationsEntity item : notificationsEntities
        ) {
            NotificationsDTO dto = modelMapper.map(item, NotificationsDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<NotificationsDTO> getByDocumentid(Integer documentid, Pageable pageable) {
        List<NotificationsDTO> results = new ArrayList<>();
        DocumentEntity document = documentRepository.findByDocumentid(documentid).orElse(null);
        List<NotificationsEntity> notificationsEntities = notificationsRepository.findByDocumentid(document,pageable);
        for (NotificationsEntity item : notificationsEntities
        ) {
            NotificationsDTO dto = modelMapper.map(item, NotificationsDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public void deleteByNotificationid(Integer notificationid) {
        notificationsRepository.deleteByNotificationid(notificationid);
    }

    @Override
    public int totalItem() {
        return (int) notificationsRepository.count();
    }
}
