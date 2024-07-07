package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.DocumentUserEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentUserRepository extends JpaRepository<DocumentUserEntity, Integer> {
    List<DocumentUserEntity> findByUserid(UsersEntity userid, Pageable pageable);
    List<DocumentUserEntity> findByDocumentid(DocumentEntity documentid, Pageable pageable);
    List<DocumentUserEntity> findByUseridAndDocumentid(UsersEntity userid,DocumentEntity documentid);
    void deleteByDocumentidAndUserid(DocumentEntity documentid, UsersEntity userid);
    void deleteByDocumentuserid(Integer documentuserid);

}
