package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.RolesEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    Optional<RolesEntity> findByRoleid(Integer roleid);

    List<RolesEntity> findByRolename(String rolename);
    List<RolesEntity> findByRolename(String rolename, Pageable pageable);
    void deleteByRoleid(Integer roleid);
    RolesEntity saveAndFlush(RolesEntity roleEntity);
}
