package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.UserRoleEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    Optional<UserRoleEntity> findByUserroleid(Integer userroleid);
    List<UserRoleEntity> findByUserid(UsersEntity userid);
    List<UserRoleEntity> findByRoleid(RolesEntity roleid);
    void deleteByUserroleid(Integer userroleid);
    UserRoleEntity saveAndFlush(UserRoleEntity userRoleEntity);
}
