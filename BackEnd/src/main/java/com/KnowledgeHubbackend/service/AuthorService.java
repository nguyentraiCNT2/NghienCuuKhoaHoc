package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.PublishersDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAll(Pageable pageable);

    List<AuthorDTO> getByAuthorname(String authorname,Pageable pageable);
    int totalItem();
    AuthorDTO getByAuthorid(Integer authorid);
    void deleteByAuthorid(Integer authorid);
    void createAuthor(AuthorDTO authorDTO);

    void updateAuthor(AuthorDTO authorDTO);
}
