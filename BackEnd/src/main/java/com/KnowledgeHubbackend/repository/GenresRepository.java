package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.GenresEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<GenresEntity, Integer> {
    @Query("select g from  GenresEntity g where g.genrename like %:genrename%")
    List<GenresEntity> findByGenrename(@Param("genrename") String genrename, Pageable pageable);
    GenresEntity findByGenreid(int id);
    void deleteByGenreid(int id);
}
