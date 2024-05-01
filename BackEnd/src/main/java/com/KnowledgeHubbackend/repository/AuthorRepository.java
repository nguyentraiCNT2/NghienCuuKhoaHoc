package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.AuthorEntity;
import com.KnowledgeHubbackend.entity.PublishersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    Optional<AuthorEntity> findByAuthorid(Integer authorid);
    List<AuthorEntity> findByAuthorname(String authorname, Pageable pageable);
    void deleteByAuthorid(Integer authorid);
    AuthorEntity saveAndFlush(AuthorEntity publishersEntity);
}
