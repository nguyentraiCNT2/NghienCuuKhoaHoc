package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.FavoriteListEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteListEntity, Integer> {
    Optional<FavoriteListEntity> findByFavoriteListid(Integer favoriteListid);

    List<FavoriteListEntity> findByDocumentid(DocumentEntity document, Pageable pageable);

    List<FavoriteListEntity> findByUserid(UsersEntity users, Pageable pageable);

    @Query("select a from FavoriteListEntity a WHERE  (:dateAdd IS NULL or  a.dateAdd = :dateAdd)")
    List<FavoriteListEntity> findByDateAdd(@Param("dateAdd") Date dateAdd, Pageable pageable);

    void deleteByFavoriteListid(Integer favoriteListid);

    FavoriteListEntity saveAndFlush(FavoriteListEntity favoriteListEntity);
}
