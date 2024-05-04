package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.CategoryEntity;
import com.KnowledgeHubbackend.entity.MenuEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByCategoryid(Integer categoryid);
    @Query("select a from  CategoryEntity a where a.categoryname like %:categoryname%")
    List<CategoryEntity> findByCategoryname(@Param("categoryname") String categoryname, Pageable pageable);
    void deleteByCategoryid(Integer categoryid);
    CategoryEntity saveAndFlush(CategoryEntity categoryEntity);
}
