package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.SuppliersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuppliersRepository extends JpaRepository<SuppliersEntity, Integer> {
    Optional<SuppliersEntity> findBySupplierid(Integer supplierid);

    List<SuppliersEntity> findBySuppliername(String suppliername);
    @Query("select a from  SuppliersEntity a where a.suppliername like %:suppliername%")
    List<SuppliersEntity> findBySuppliername(@Param("suppliername") String suppliername, Pageable pageable);
    void deleteBySupplierid(Integer supplierid);
    SuppliersEntity saveAndFlush(SuppliersEntity suppliersEntity);
}
