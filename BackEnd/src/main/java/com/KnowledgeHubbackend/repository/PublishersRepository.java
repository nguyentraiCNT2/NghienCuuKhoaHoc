package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.PublishersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublishersRepository extends JpaRepository<PublishersEntity, Integer> {
    Optional<PublishersEntity> findByPublisherid(Integer publisherid);

    List<PublishersEntity> findByPublishername(String publishername);
    List<PublishersEntity> findByPublishername(String publishername, Pageable pageable);
    void deleteByPublisherid(Integer publisherid);
    PublishersEntity saveAndFlush(PublishersEntity publishersEntity);
}
