package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.PublishersDTO;
import com.KnowledgeHubbackend.dto.SuppliersDTO;
import com.KnowledgeHubbackend.entity.PublishersEntity;
import com.KnowledgeHubbackend.entity.SuppliersEntity;
import com.KnowledgeHubbackend.repository.PublishersRepository;
import com.KnowledgeHubbackend.service.PublishersService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishersServiceIMPL implements PublishersService {
    @Autowired
    private final PublishersRepository publishersRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public PublishersServiceIMPL(PublishersRepository publishersRepository, ModelMapper modelMapper) {
        this.publishersRepository = publishersRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<PublishersDTO> getAll(Pageable pageable) {
        List<PublishersDTO> results = new ArrayList<>();
        List<PublishersEntity> publishersEntities = publishersRepository.findAll(pageable).getContent();
        for (PublishersEntity item: publishersEntities
        ) {
            PublishersDTO dto = modelMapper.map(item,PublishersDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<PublishersDTO> getByPublishername(String publishername, Pageable pageable) {
        List<PublishersDTO> results = new ArrayList<>();
        List<PublishersEntity> publishersEntities = publishersRepository.findByPublishername(publishername,pageable);
        for (PublishersEntity item: publishersEntities
        ) {
            PublishersDTO dto = modelMapper.map(item,PublishersDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) publishersRepository.count();
    }

    @Override
    public PublishersDTO getByPublisherid(Integer publisherid) {
        try {
            PublishersEntity publishers = publishersRepository.findByPublisherid(publisherid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + publisherid));
            return modelMapper.map(publishers,PublishersDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteByPublisherid(Integer publisherid) {
        publishersRepository.deleteByPublisherid(publisherid);
    }

    @Override
    public void createPublisher(PublishersDTO publishersDTO) {
        if (publishersDTO != null) {
            PublishersEntity publishers =  modelMapper.map(publishersDTO, PublishersEntity.class);
            if (publishers != null ) {
                publishersRepository.save(publishers);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void updatePublisher(PublishersDTO publishersDTO) {
        PublishersEntity existingPublishers  = publishersRepository.findByPublisherid(publishersDTO.getPublisherid())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(publishersDTO, existingPublishers);
        publishersRepository.save(existingPublishers);
    }
}
