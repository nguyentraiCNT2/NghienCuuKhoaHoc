package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.CategoryDTO;
import com.KnowledgeHubbackend.dto.MenuDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll(Pageable pageable);
    @Query("select a from  CategoryEntity a where a.categoryname like % :categoryname %")
    List<CategoryDTO> getByCategoryname(String categoryname,Pageable pageable);
    int totalItem();
    CategoryDTO getByCategoryid(Integer categoryid);
    void deleteByCategoryid(Integer categoryid);
    void createCategory(CategoryDTO categoryDTO);

    void updateCategory(CategoryDTO categoryDTO);
}
