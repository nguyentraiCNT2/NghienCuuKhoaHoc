package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.HistoryDTO;
import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.HistoryEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.DocumentRepository;
import com.KnowledgeHubbackend.repository.HistoryRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.service.HistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceIMPL implements HistoryService {
    @Autowired
    private final HistoryRepository historyRepository;
    @Autowired
    private final ModelMapper modelMapper;
@Autowired
private final UserRepository userRepository;
@Autowired
private final DocumentRepository documentRepository;
    public HistoryServiceIMPL(HistoryRepository historyRepository, ModelMapper modelMapper, UserRepository userRepository, DocumentRepository documentRepository) {
        this.historyRepository = historyRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<HistoryDTO> getAll(Pageable pageable) {
        List<HistoryDTO> results = new ArrayList<>();
        List<HistoryEntity> historyEntities = historyRepository.findAll(pageable).getContent();
        for (HistoryEntity item : historyEntities
        ) {
            HistoryDTO dto = modelMapper.map(item, HistoryDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<HistoryDTO> getByUserid(String userid, Pageable pageable) {
        List<HistoryDTO> results = new ArrayList<>();
        UsersEntity users = userRepository.findByUserid(userid).orElse(null);
        List<HistoryEntity> historyEntities = historyRepository.findByUserid(users,pageable);
        for (HistoryEntity item : historyEntities
        ) {
            if (item.getStatus()==true ){
                HistoryDTO dto = modelMapper.map(item, HistoryDTO.class);
                results.add(dto);
            }

        }
        return results;
    }

    @Override
    public List<HistoryDTO> getByDocumentid(Integer documentid, Pageable pageable) {
        List<HistoryDTO> results = new ArrayList<>();
        DocumentEntity document = documentRepository.findByDocumentid(documentid).orElse(null);
        List<HistoryEntity> historyEntities = historyRepository.findByDocumentid(document,pageable);
        for (HistoryEntity item : historyEntities
        ) {
            HistoryDTO dto = modelMapper.map(item, HistoryDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<HistoryDTO> getByDocumentid(Integer documentid) {
        List<HistoryDTO> results = new ArrayList<>();
        DocumentEntity document = documentRepository.findByDocumentid(documentid).orElse(null);
        List<HistoryEntity> historyEntities = historyRepository.findByDocumentid(document);
        for (HistoryEntity item : historyEntities
        ) {
            HistoryDTO dto = modelMapper.map(item, HistoryDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public void deleteByHistoryid(Integer historyid) {
        historyRepository.deleteByHistoryid(historyid);
    }

    @Override
    public int totalItem() {
        return (int) historyRepository.count();
    }
}
