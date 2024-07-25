package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.CategoryDTO;
import com.KnowledgeHubbackend.dto.GenresDTO;
import com.KnowledgeHubbackend.entity.CategoryEntity;
import com.KnowledgeHubbackend.entity.GenresEntity;
import com.KnowledgeHubbackend.repository.CategoryRepository;
import com.KnowledgeHubbackend.repository.GenresRepository;
import com.KnowledgeHubbackend.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final GenresRepository genresRepository;

    public CategoryServiceIMPL(CategoryRepository categoryRepository, ModelMapper modelMapper, GenresRepository genresRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.genresRepository = genresRepository;
    }

    @Override
    public List<CategoryDTO> getAll(Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable).getContent();
        for (CategoryEntity item: categoryEntities
        ) {
            CategoryDTO dto = modelMapper.map(item,CategoryDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<CategoryDTO> getByGenres(Integer genreid, Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        GenresEntity genresEntity = genresRepository.findById(genreid).orElseThrow(null);
        List<CategoryEntity> categoryEntities = categoryRepository.findByGenres(genresEntity,pageable);
        for (CategoryEntity item: categoryEntities
        ) {
            CategoryDTO dto = modelMapper.map(item,CategoryDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<CategoryDTO> getByCategoryname(String categoryname, Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findByCategoryname(categoryname,pageable);
        for (CategoryEntity item: categoryEntities
        ) {
            CategoryDTO dto = modelMapper.map(item,CategoryDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    public CategoryDTO getByCategoryid(Integer categoryid) {
        try {
            CategoryEntity category = categoryRepository.findByCategoryid(categoryid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + categoryid));
            return modelMapper.map(category,CategoryDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteByCategoryid(Integer categoryid) {
        categoryRepository.deleteByCategoryid(categoryid);
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        if (categoryDTO != null) {
            CategoryEntity category =  modelMapper.map(categoryDTO, CategoryEntity.class);
            if (category != null ) {
                categoryRepository.save(category);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        // Tìm CategoryEntity hiện có
        CategoryEntity existingCategory = categoryRepository.findByCategoryid(categoryDTO.getCategoryid())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu Category"));

        // Tìm GenresEntity hiện có
        GenresEntity genresEntity = genresRepository.findByGenreid(categoryDTO.getGenres().getGenreid());
        if (genresEntity == null) {
            throw new RuntimeException("Không tìm thấy dữ liệu Genres");
        }

        // Cập nhật các thuộc tính cần thiết, tránh thay đổi genreid
        existingCategory.setCategoryname(categoryDTO.getCategoryname());
        existingCategory.setDescription(categoryDTO.getDescription());
        existingCategory.setGenres(genresEntity); // Chỉ cần thiết lập lại GenresEntity mà không thay đổi genreid

        // Lưu lại CategoryEntity đã được cập nhật
        categoryRepository.save(existingCategory);
    }
}
