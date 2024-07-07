package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.DocumentUserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocumentUserService {
    List<DocumentUserDTO> getByUserid(String token, Pageable pageable);

    List<DocumentUserDTO> getByDocumentid(Integer documentid, Pageable pageable);
    void  create(DocumentUserDTO dto, String token);
    int   totalItem();
}
