package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.HistoryEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer> {
    List<HistoryEntity> findByUserid(UsersEntity users, Pageable pageable);
    List<HistoryEntity> findByDocumentid(DocumentEntity document, Pageable pageable);
    List<HistoryEntity> findByDocumentid(DocumentEntity document);
    void deleteByHistoryid(Integer historyid);
}
