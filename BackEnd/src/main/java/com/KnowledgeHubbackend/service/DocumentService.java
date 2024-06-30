package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.DocumentDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    List<DocumentDTO> getAll(Pageable pageable);
    List<DocumentDTO> getAllByViewsOrderByDesc(Pageable pageable);
    List<DocumentDTO> getAllByViewsOrderByAsc(Pageable pageable);
    List<DocumentDTO> getAllByCountDownloadOrderByDesc(Pageable pageable);
    List<DocumentDTO> getAllByCountDownloadOrderByAsc(Pageable pageable);
    List<DocumentDTO> getByDocumentname(String documentname,Pageable pageable);
    List<DocumentDTO> getByCategoryid(Integer categoryid, Pageable pageable);
    List<DocumentDTO> getByAuthorID(Integer authorID, Pageable pageable);
    List<DocumentDTO> getByPublisherid(Integer publisherid, Pageable pageable);
    List<DocumentDTO> getBySupplierid(Integer supplierid, Pageable pageable);
    int totalItem();
    DocumentDTO getByDocumentid(Integer documentid);
    DocumentDTO getByDocumentidaddhistory(Integer documentid, String userid);
    void deleteByDocumentid(Integer documentid)throws IOException;
    void createDocument(DocumentDTO documentDTO, MultipartFile file, String userid) throws IOException;

    void updateDocument(DocumentDTO documentDTO,MultipartFile file, String  userid)throws IOException;
}
