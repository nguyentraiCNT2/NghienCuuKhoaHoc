package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findByUserid(String userid);
    List<UsersEntity> findByUsername(String username, Pageable pageable);
    List<UsersEntity> findByUsername(String username);
    List<UsersEntity> findByEmail(String email);
    List<UsersEntity> findByEmail(String email, Pageable pageable);
    List<UsersEntity> findByStatus(Boolean status);
    List<UsersEntity> findByPhone(String phone);
    List<UsersEntity> findByPhone(String phone, Pageable pageable);
    void deleteByUserid(String userid);
    UsersEntity saveAndFlush(UsersEntity userEntity);
}
