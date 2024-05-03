package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.dto.SuppliersDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuppliersService {
    List<SuppliersDTO> getAll(Pageable pageable);
    @Query("select a from  SuppliersEntity a where a.suppliername like % :suppliername %")
    List<SuppliersDTO> getBySuppliername(String suppliername,Pageable pageable);
    int totalItem();
    SuppliersDTO getBySupplierid(Integer supplierid);
    void deleteBySupplierid(Integer supplierid);
    void createSuppliers(SuppliersDTO suppliersDTO);

    void updateSuppliers(SuppliersDTO suppliersDTO);

}
