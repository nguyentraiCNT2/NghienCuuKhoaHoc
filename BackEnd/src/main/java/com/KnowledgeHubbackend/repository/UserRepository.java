package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findByUserid(String userid);
    @Query("select a from  UsersEntity a where a.username like %:username%")
    List<UsersEntity> findByUsername( @Param("username") String username, Pageable pageable);
    List<UsersEntity> findByUsername(String username);
    List<UsersEntity> findByEmail(String email);
    @Query("select a from  UsersEntity a where a.email like %:email%")
    List<UsersEntity> findByEmail( @Param("email") String email, Pageable pageable);
    List<UsersEntity> findByStatus(Boolean status);
    List<UsersEntity> findByPhone(String phone);
    @Query("select a from  UsersEntity a where a.phone like %:phone%")
    List<UsersEntity> findByPhone(@Param("phone") String phone, Pageable pageable);

    void deleteByUserid(String userid);
    UsersEntity saveAndFlush(UsersEntity userEntity);
}
