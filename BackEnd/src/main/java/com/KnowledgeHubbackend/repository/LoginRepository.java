package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository  extends JpaRepository<UsersEntity, String> {
    UsersEntity findByUsernameAndPassword(String username,String password);
    UsersEntity saveAndFlush(UsersEntity userEntity);
}
