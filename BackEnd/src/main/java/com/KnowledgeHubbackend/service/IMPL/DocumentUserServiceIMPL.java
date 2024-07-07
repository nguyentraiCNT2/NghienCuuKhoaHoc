package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.dto.DocumentUserDTO;
import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.DocumentUserEntity;
import com.KnowledgeHubbackend.entity.UserTokenEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.DocumentRepository;
import com.KnowledgeHubbackend.repository.DocumentUserRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.repository.UserTokenRepository;
import com.KnowledgeHubbackend.service.DocumentUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentUserServiceIMPL implements DocumentUserService {
    @Autowired
    private DocumentUserRepository documentUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserTokenRepository userTokenRepository;
    @Override
    public List<DocumentUserDTO> getByUserid(String token, Pageable pageable) {
        List<DocumentUserDTO> results = new ArrayList<>();
        List<UserTokenEntity> userTokenEntity = userTokenRepository.findByToken(token);
        List<DocumentUserEntity> documentUserEntities = documentUserRepository.findByUserid(userTokenEntity.get(0).getUsers(), pageable);
        for (DocumentUserEntity item : documentUserEntities
        ) {
            DocumentUserDTO dto = modelMapper.map(item, DocumentUserDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<DocumentUserDTO> getByDocumentid(Integer documentid, Pageable pageable) {
        List<DocumentUserDTO> results = new ArrayList<>();
        DocumentEntity document = documentRepository.findByDocumentid(documentid).orElse(null);
        List<DocumentUserEntity> documentUserEntities = documentUserRepository.findByDocumentid(document, pageable);
        for (DocumentUserEntity item : documentUserEntities
        ) {
            DocumentUserDTO dto = modelMapper.map(item, DocumentUserDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public void create(DocumentUserDTO dto, String token) {
        if (dto != null) {
            // Lấy thông tin User từ token
            List<UserTokenEntity> userTokenEntities = userTokenRepository.findByToken(token);
            UsersEntity userEntity = userTokenEntities.get(0).getUsers();

            // Tìm DocumentEntity dựa trên documentid từ DTO
            DocumentEntity documentEntity = documentRepository.findByDocumentid(dto.getDocumentid().getDocumentid()).orElse(null);

            // Tìm và xóa tất cả các bản ghi DocumentUserEntity dựa trên userid và documentid
            List<DocumentUserEntity> documentUserEntities = documentUserRepository.findByUseridAndDocumentid(userEntity, documentEntity);
            for (DocumentUserEntity documentUserEntity : documentUserEntities) {
                documentUserRepository.delete(documentUserEntity);
            }

            // Lưu DocumentUserEntity mới từ DTO
            DocumentUserEntity newDocumentUserEntity = modelMapper.map(dto, DocumentUserEntity.class);
            newDocumentUserEntity.setDocumentid(documentEntity);
            newDocumentUserEntity.setUserid(userEntity);
            documentUserRepository.save(newDocumentUserEntity);
        }
    }

    @Override
    public int totalItem() {
        return (int) documentUserRepository.count();
    }
}
