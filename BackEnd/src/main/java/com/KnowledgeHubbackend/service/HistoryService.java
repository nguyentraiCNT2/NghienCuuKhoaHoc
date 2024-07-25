package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.dto.HistoryDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HistoryService {
    List<HistoryDTO> getAll(Pageable pageable);
    List<HistoryDTO> getByUserid(String userid,Pageable pageable);
    List<HistoryDTO> getByDocumentid(Integer documentid, Pageable pageable);
    List<HistoryDTO> getByDocumentid(Integer documentid);
    void deleteByHistoryid(Integer historyid);
    int totalItem();
}
