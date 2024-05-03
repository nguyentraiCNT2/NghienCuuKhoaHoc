package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.PublishersDTO;
import com.KnowledgeHubbackend.dto.SuppliersDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublishersService {
    List<PublishersDTO> getAll(Pageable pageable);
    @Query("select a from  PublishersEntity a where a.publishername like % :publishername %")
    List<PublishersDTO> getByPublishername(String publishername,Pageable pageable);
    int totalItem();
    PublishersDTO getByPublisherid(Integer publisherid);
    void deleteByPublisherid(Integer publisherid);
    void createPublisher(PublishersDTO publishersDTO);

    void updatePublisher(PublishersDTO publishersDTO);
}
