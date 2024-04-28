package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    Optional<RolesEntity> findByRoleid(Long roleid);

    List<RolesEntity> findByRolename(String rolename);
    void deleteByRoleid(Long roleid);
    RolesEntity saveAndFlush(RolesEntity roleEntity);
}
