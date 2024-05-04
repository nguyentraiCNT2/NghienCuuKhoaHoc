package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.RolesEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    Optional<RolesEntity> findByRoleid(Integer roleid);
    @Query("select a from  RolesEntity a where a.rolename like %:rolename%")
    List<RolesEntity> findByRolename(@Param("rolename") String rolename);
    List<RolesEntity> findByRolename(String rolename, Pageable pageable);
    void deleteByRoleid(Integer roleid);
    RolesEntity saveAndFlush(RolesEntity roleEntity);
}
