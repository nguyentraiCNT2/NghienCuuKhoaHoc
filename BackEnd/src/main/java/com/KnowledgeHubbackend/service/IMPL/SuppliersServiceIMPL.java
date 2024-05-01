package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.dto.SuppliersDTO;
import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.SuppliersEntity;
import com.KnowledgeHubbackend.repository.SuppliersRepository;
import com.KnowledgeHubbackend.service.SuppliersService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuppliersServiceIMPL implements SuppliersService {
    @Autowired
    private final SuppliersRepository suppliersRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public SuppliersServiceIMPL(SuppliersRepository suppliersRepository, ModelMapper modelMapper) {
        this.suppliersRepository = suppliersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SuppliersDTO> getAll(Pageable pageable) {
        List<SuppliersDTO> results = new ArrayList<>();
        List<SuppliersEntity> suppliersEntities = suppliersRepository.findAll(pageable).getContent();
        for (SuppliersEntity item: suppliersEntities
        ) {
            SuppliersDTO dto = modelMapper.map(item,SuppliersDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<SuppliersDTO> getBySuppliername(String suppliername, Pageable pageable) {
        List<SuppliersDTO> results = new ArrayList<>();
        List<SuppliersEntity> suppliersEntities = suppliersRepository.findBySuppliername(suppliername,pageable);
        for (SuppliersEntity item: suppliersEntities
        ) {
            SuppliersDTO dto = modelMapper.map(item,SuppliersDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) suppliersRepository.count();
    }

    @Override
    public SuppliersDTO getBySupplierid(Integer supplierid) {
        try {
            SuppliersEntity suppliers = suppliersRepository.findBySupplierid(supplierid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + supplierid));
            return modelMapper.map(suppliers,SuppliersDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteBySupplierid(Integer supplierid) {
        suppliersRepository.deleteBySupplierid(supplierid);
    }

    @Override
    public void createSuppliers(SuppliersDTO suppliersDTO) {
        if (suppliersDTO != null) {
            SuppliersEntity suppliers =  modelMapper.map(suppliersDTO, SuppliersEntity.class);
            if (suppliers != null ) {
                suppliersRepository.save(suppliers);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void updateSuppliers(SuppliersDTO suppliersDTO) {
        SuppliersEntity existingSuppliers  = suppliersRepository.findBySupplierid(suppliersDTO.getSupplierid())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(suppliersDTO, existingSuppliers);
        suppliersRepository.save(existingSuppliers);
    }
}
