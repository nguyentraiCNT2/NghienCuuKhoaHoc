package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.GenresDTO;
import com.KnowledgeHubbackend.entity.AuthorEntity;
import com.KnowledgeHubbackend.entity.GenresEntity;
import com.KnowledgeHubbackend.repository.GenresRepository;
import com.KnowledgeHubbackend.service.GenresService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenresServiceINPL implements GenresService {
    @Autowired
    private  GenresRepository genresRepository;
    @Autowired
    private  ModelMapper modelMapper;
    @Override
    public List<GenresDTO> getAll(Pageable pageable) {
        List<GenresDTO> results = new ArrayList<>();
        List<GenresEntity> genresEntities = genresRepository.findAll(pageable).getContent();
        for (GenresEntity item: genresEntities
        ) {
            GenresDTO dto = modelMapper.map(item,GenresDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<GenresDTO> findByGenrename(String genrename, Pageable pageable) {
        List<GenresDTO> results = new ArrayList<>();
        List<GenresEntity> genresEntities = genresRepository.findByGenrename(genrename,pageable);
        for (GenresEntity item: genresEntities
        ) {
            GenresDTO dto = modelMapper.map(item,GenresDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public GenresDTO getByGenreid(int id) {
        try {
            GenresEntity genres = genresRepository.findByGenreid(id);
            return modelMapper.map(genres,GenresDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public int totalItem() {
        return (int) genresRepository.count();
    }

    @Override
    public void save(GenresDTO genresDTO) {
        if (genresDTO != null) {
            GenresEntity genres =  modelMapper.map(genresDTO, GenresEntity.class);
            if (genres != null ) {

                genresRepository.save(genres);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void deleteByGenreid(int id) {
        genresRepository.deleteByGenreid(id);
    }

    @Override
    public void update(GenresDTO genresDTO) {
        GenresEntity genresEntity = genresRepository.findByGenreid(genresDTO.getGenreid());
        modelMapper.map(genresDTO, genresEntity);
        genresRepository.save(genresEntity);
    }
}
