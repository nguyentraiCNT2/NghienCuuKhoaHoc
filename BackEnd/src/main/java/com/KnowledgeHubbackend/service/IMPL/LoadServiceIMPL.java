package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.dto.HistoryDTO;
import com.KnowledgeHubbackend.dto.LoadsDTO;
import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.HistoryEntity;
import com.KnowledgeHubbackend.entity.LoadsEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.DocumentRepository;
import com.KnowledgeHubbackend.repository.DownloaderRepository;
import com.KnowledgeHubbackend.repository.LoadsRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.service.LoadService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadServiceIMPL implements LoadService {
    @Autowired
    private final LoadsRepository loadsRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final DownloaderRepository downloaderRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final DocumentRepository documentRepository;

    public LoadServiceIMPL(LoadsRepository loadsRepository, ModelMapper modelMapper, DownloaderRepository downloaderRepository, UserRepository userRepository, DocumentRepository documentRepository) {
        this.loadsRepository = loadsRepository;
        this.modelMapper = modelMapper;
        this.downloaderRepository = downloaderRepository;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<LoadsDTO> getAll(Pageable pageable) {
        List<LoadsDTO> results = new ArrayList<>();
        List<LoadsEntity> loadsEntities = loadsRepository.findAll(pageable).getContent();
        for (LoadsEntity item : loadsEntities
        ) {
            LoadsDTO dto = modelMapper.map(item, LoadsDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public LoadsDTO getById(Integer id) {
        try {
            LoadsEntity loads = loadsRepository.findByLoadid(id)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + id));
            return modelMapper.map(loads, LoadsDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public List<LoadsDTO> getByUserId(String userid, Pageable pageable) {
        List<LoadsDTO> results = new ArrayList<>();
        UsersEntity users = userRepository.findByUserid(userid).orElse(null);
        List<LoadsEntity> loadsEntities = loadsRepository.findByUserid(users, pageable);
        for (LoadsEntity item : loadsEntities
        ) {
            LoadsDTO dto = modelMapper.map(item, LoadsDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<LoadsDTO> getByDocumentid(Integer documentid, Pageable pageable) {
        List<LoadsDTO> results = new ArrayList<>();
        DocumentEntity document = documentRepository.findByDocumentid(documentid).orElse(null);
        List<LoadsEntity> loadsEntities = loadsRepository.findByDocumentid(document, pageable);
        for (LoadsEntity item : loadsEntities
        ) {
            LoadsDTO dto = modelMapper.map(item, LoadsDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public void create(LoadsDTO loadsDTO) {
        if (loadsDTO != null) {
            LoadsEntity loads = modelMapper.map(loadsDTO, LoadsEntity.class);
            LocalDate currentDate = LocalDate.now();
            Date currentSqlDate = Date.valueOf(currentDate);
            UsersEntity users = userRepository.findByUserid(loadsDTO.getUserid().getUserid()).orElse(null);
            DocumentEntity document = documentRepository.findByDocumentid(loadsDTO.getDocumentID().getDocumentid()).orElse(null);
            loads.setUserid(users);
            loads.setDocumentID(document);
            loads.setDateDown(currentSqlDate);
            loadsRepository.save(loads);

        }
    }



    @Override
    public void delete(Integer id) {
    loadsRepository.deleteByLoadid(id);
    }

    @Override
    public int totalItem() {
        return (int) loadsRepository.count();
    }
}
