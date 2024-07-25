package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.PublishersDTO;
import com.KnowledgeHubbackend.entity.AuthorEntity;
import com.KnowledgeHubbackend.entity.HistoryEntity;
import com.KnowledgeHubbackend.entity.PublishersEntity;
import com.KnowledgeHubbackend.repository.AuthorRepository;
import com.KnowledgeHubbackend.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceIMPL implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public AuthorServiceIMPL(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<AuthorDTO> getAll(Pageable pageable) {
        List<AuthorDTO> results = new ArrayList<>();
        List<AuthorEntity> authorEntities = authorRepository.findAll(pageable).getContent();
        for (AuthorEntity item: authorEntities
        ) {
            AuthorDTO dto = modelMapper.map(item,AuthorDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<AuthorDTO> getByAuthorname(String authorname, Pageable pageable) {
        List<AuthorDTO> results = new ArrayList<>();
        List<AuthorEntity> authorEntities = authorRepository.findByAuthorname(authorname,pageable);
        for (AuthorEntity item: authorEntities
        ) {
            AuthorDTO dto = modelMapper.map(item,AuthorDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) authorRepository.count();
    }

    @Override
    public AuthorDTO getByAuthorid(Integer authorid) {
        try {
            AuthorEntity author = authorRepository.findByAuthorid(authorid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + authorid));
            return modelMapper.map(author,AuthorDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteByAuthorid(Integer authorid) {
        authorRepository.deleteByAuthorid(authorid);
    }

    @Override
    public void createAuthor(AuthorDTO authorDTO) {
        if (authorDTO != null) {
            AuthorEntity author =  modelMapper.map(authorDTO, AuthorEntity.class);
            if (author != null ) {

                authorRepository.save(author);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void updateAuthor(AuthorDTO authorDTO) {
        AuthorEntity existingAuthor  = authorRepository.findByAuthorid(authorDTO.getAuthorid())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(authorDTO, existingAuthor);
        authorRepository.save(existingAuthor);
    }
}
