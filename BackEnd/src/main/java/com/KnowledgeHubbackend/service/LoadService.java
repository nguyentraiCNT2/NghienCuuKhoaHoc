package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.LoadsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadService {
    List<LoadsDTO> getAll(Pageable pageable);

    LoadsDTO getById(Integer id);

    List<LoadsDTO> getByUserId(String userid, Pageable pageable);

    List<LoadsDTO> getByDocumentid(Integer documentid, Pageable pageable);

    void create(LoadsDTO loadsDTO);

    void delete(Integer id);

    int totalItem();
}