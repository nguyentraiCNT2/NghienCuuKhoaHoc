package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.HistoryEntity;
import com.KnowledgeHubbackend.entity.LoadsEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoadsRepository extends JpaRepository<LoadsEntity, Integer> {
    Optional<LoadsEntity> findByLoadid(Integer loadid);
    List<LoadsEntity> findByUserid(UsersEntity users, Pageable pageable);
    List<LoadsEntity> findByDocumentID(DocumentEntity document, Pageable pageable);
    void deleteByLoadid(Integer loadid);
}
