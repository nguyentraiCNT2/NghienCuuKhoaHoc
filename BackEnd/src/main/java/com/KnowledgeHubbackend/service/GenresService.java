package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.GenresDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenresService {
    List<GenresDTO> getAll(Pageable pageable);

    List<GenresDTO> findByGenrename(String genrename, Pageable pageable);

    GenresDTO getByGenreid(int id);
    int totalItem();

    void save(GenresDTO genresDTO);

    void deleteByGenreid(int id);

    void update(GenresDTO genresDTO);
}
