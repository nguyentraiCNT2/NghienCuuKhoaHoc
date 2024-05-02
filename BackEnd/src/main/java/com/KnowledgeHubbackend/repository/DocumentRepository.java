package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
    Optional<DocumentEntity> findByDocumentid(Integer documentid);
    List<DocumentEntity> findByDocumentname(String documentname, Pageable pageable);
    List<DocumentEntity> findByCategoryid(CategoryEntity categoryid, Pageable pageable);
    List<DocumentEntity> findByAuthorID(AuthorEntity authorID, Pageable pageable);
    List<DocumentEntity> findByPublisherid(PublishersEntity publisherid, Pageable pageable);
    List<DocumentEntity> findByMenuid(MenuEntity menuid, Pageable pageable);
    List<DocumentEntity> findBySupplierid(SuppliersEntity supplierid, Pageable pageable);
    @Query("SELECT d FROM DocumentEntity d ORDER BY  d.views desc " )
    List<DocumentEntity> findAllByViewsOrderByDesc( Pageable pageable);
    @Query("SELECT d FROM DocumentEntity d ORDER BY  d.views desc " )
    List<DocumentEntity> findAllByViewsOrderByAsc( Pageable pageable);
    @Query("SELECT d FROM DocumentEntity d ORDER BY  d.countDownload desc " )
    List<DocumentEntity> findAllByCountDownloadOrderByDesc( Pageable pageable);
    @Query("SELECT d FROM DocumentEntity d ORDER BY  d.countDownload asc " )
    List<DocumentEntity> findAllByCountDownloadOrderByAsc( Pageable pageable);
    void deleteByDocumentid(Integer Documentid);
    DocumentEntity saveAndFlush(DocumentEntity downloaderEntity);
}
