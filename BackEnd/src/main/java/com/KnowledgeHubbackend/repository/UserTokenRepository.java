package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.UserTokenEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Integer> {
    List<UserTokenEntity> findByUsers(UsersEntity users);
    List<UserTokenEntity> findByToken(String token);
    @Query("delete from UserTokenEntity ut where ut.users = :users")
    void deleteByUsers(@Param("users") UsersEntity users);
    void deleteByTokenid(Integer tokenid);

}
