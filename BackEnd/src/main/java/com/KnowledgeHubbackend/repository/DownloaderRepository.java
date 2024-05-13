package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.DownloaderEntity;
import com.KnowledgeHubbackend.entity.LoadsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DownloaderRepository extends JpaRepository<DownloaderEntity, Integer> {
    Optional<DownloaderEntity> findByDownID(Integer downID);
    List<DownloaderEntity> findByDownName(String downName , Pageable pageable);
    List<DownloaderEntity> findByDownEmail(String downEmail, Pageable pageable);
    List<DownloaderEntity> findByDownPhone(String downPhone, Pageable pageable);
    List<DownloaderEntity> findByLoadid(LoadsEntity loads, Pageable pageable);
    DownloaderEntity saveAndFlush(DownloaderEntity downloaderEntity);
}
