package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.CategoryEntity;
import com.KnowledgeHubbackend.entity.DownloaderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DownloadersRepository extends JpaRepository<DownloaderEntity, Integer> {
    Optional<DownloaderEntity> findByDownID(Integer downID);
    List<DownloaderEntity> findByDownName(String downName, Pageable pageable);
    void deleteByDownID(Integer downID);
    DownloaderEntity saveAndFlush(DownloaderEntity downloaderEntity);

}
