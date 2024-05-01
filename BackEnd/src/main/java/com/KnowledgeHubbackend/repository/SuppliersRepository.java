package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.SuppliersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuppliersRepository extends JpaRepository<SuppliersEntity, Integer> {
    Optional<SuppliersEntity> findBySupplierid(Integer supplierid);

    List<SuppliersEntity> findBySuppliername(String suppliername);
    List<SuppliersEntity> findBySuppliername(String suppliername, Pageable pageable);
    void deleteBySupplierid(Integer supplierid);
    SuppliersEntity saveAndFlush(SuppliersEntity suppliersEntity);
}
