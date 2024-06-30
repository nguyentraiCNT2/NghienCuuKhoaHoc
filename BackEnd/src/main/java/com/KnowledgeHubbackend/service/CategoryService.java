package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll(Pageable pageable);

    List<CategoryDTO> getByCategoryname(String categoryname,Pageable pageable);
    int totalItem();
    CategoryDTO getByCategoryid(Integer categoryid);
    void deleteByCategoryid(Integer categoryid);
    void createCategory(CategoryDTO categoryDTO);

    void updateCategory(CategoryDTO categoryDTO);
}
