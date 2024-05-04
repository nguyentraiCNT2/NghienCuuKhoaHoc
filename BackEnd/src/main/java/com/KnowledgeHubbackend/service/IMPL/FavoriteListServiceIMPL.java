package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.dto.FavoriteListDTO;
import com.KnowledgeHubbackend.dto.MenuDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.*;
import com.KnowledgeHubbackend.repository.DocumentRepository;
import com.KnowledgeHubbackend.repository.FavoriteListRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.service.FavoriteListService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FavoriteListServiceIMPL implements FavoriteListService {
    @Autowired
    private final FavoriteListRepository favoriteListRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final DocumentRepository documentRepository;

    public FavoriteListServiceIMPL(FavoriteListRepository favoriteListRepository, ModelMapper modelMapper, UserRepository userRepository, DocumentRepository documentRepository) {
        this.favoriteListRepository = favoriteListRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<FavoriteListDTO> getAll(Pageable pageable) {
        List<FavoriteListDTO> results = new ArrayList<>();
        List<FavoriteListEntity> favoriteListEntities = favoriteListRepository.findAll(pageable).getContent();
        for (FavoriteListEntity item: favoriteListEntities
        ) {
            FavoriteListDTO dto = modelMapper.map(item,FavoriteListDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public FavoriteListDTO getByFavoriteListid(Integer favoriteListid) {
        try {
            FavoriteListEntity favoriteList = favoriteListRepository.findByFavoriteListid(favoriteListid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + favoriteListid));
            return modelMapper.map(favoriteList,FavoriteListDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public List<FavoriteListDTO> getByDateAdd(Date dateAdd, Pageable pageable) {
        List<FavoriteListDTO> results = new ArrayList<>();
        List<FavoriteListEntity> favoriteListEntities = favoriteListRepository.findByDateAdd(dateAdd,pageable);
        for (FavoriteListEntity item: favoriteListEntities
        ) {
            FavoriteListDTO dto = modelMapper.map(item,FavoriteListDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<FavoriteListDTO> getByDocumentid(Integer documentid, Pageable pageable) {
        List<FavoriteListDTO> results = new ArrayList<>();
        DocumentEntity document = documentRepository.findByDocumentid(documentid).orElse(null);
        List<FavoriteListEntity> favoriteListEntities = favoriteListRepository.findByDocumentid(document,pageable);
        for (FavoriteListEntity item: favoriteListEntities
        ) {
            FavoriteListDTO dto = modelMapper.map(item,FavoriteListDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<FavoriteListDTO> getByUserid(String userid, Pageable pageable) {
        List<FavoriteListDTO> results = new ArrayList<>();
        UsersEntity users = userRepository.findByUserid(userid).orElse(null);
        List<FavoriteListEntity> favoriteListEntities = favoriteListRepository.findByUserid(users,pageable);
        for (FavoriteListEntity item: favoriteListEntities
        ) {
            FavoriteListDTO dto = modelMapper.map(item,FavoriteListDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) favoriteListRepository.count();
    }

    @Override
    public void deleteByFavoriteListid(Integer favoriteListid) {
        favoriteListRepository.deleteByFavoriteListid(favoriteListid);
    }

    @Override
    public void createFavoriteList(FavoriteListDTO favoriteListDTO) {
        if (favoriteListDTO != null) {
            FavoriteListEntity favoriteList = modelMapper.map(favoriteListDTO, FavoriteListEntity.class);
            DocumentEntity document = documentRepository.findByDocumentid(favoriteListDTO.getDocumentid().getDocumentid()).orElse(null);
            UsersEntity users = userRepository.findByUserid(favoriteListDTO.getUserid().getUserid()).orElse(null);
            favoriteList.setDocumentid(document);
            favoriteList.setUserid(users);
            favoriteListRepository.save(favoriteList);
        }
    }

    @Override
    public void updateFavoriteList(FavoriteListDTO favoriteListDTO) {
        if (favoriteListDTO != null) {
            FavoriteListEntity existingFavorite = favoriteListRepository.findByFavoriteListid(favoriteListDTO.getFavoriteListid())
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + favoriteListDTO.getFavoriteListid()));
            Date dateadd = existingFavorite.getDateAdd();
            DocumentEntity document = documentRepository.findByDocumentid(favoriteListDTO.getDocumentid().getDocumentid()).orElse(null);
            UsersEntity users = userRepository.findByUserid(favoriteListDTO.getUserid().getUserid()).orElse(null);
            DocumentDTO documentDTO =  modelMapper.map(document,DocumentDTO.class);
            UsersDTO usersDTO =  modelMapper.map(users,UsersDTO.class);
            favoriteListDTO.setDocumentid(documentDTO);
            favoriteListDTO.setUserid(usersDTO);
            favoriteListDTO.setDateAdd(dateadd);
            modelMapper.map(favoriteListDTO,existingFavorite );
            favoriteListRepository.save(existingFavorite);
        }
    }
}
