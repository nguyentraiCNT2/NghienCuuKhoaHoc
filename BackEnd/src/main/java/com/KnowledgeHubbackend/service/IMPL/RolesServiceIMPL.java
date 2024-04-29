package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.algorithm.RandomId;
import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.UserRoleEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.RolesRepository;
import com.KnowledgeHubbackend.service.RolesService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RolesServiceIMPL implements RolesService {
    @Autowired
    private final RolesRepository rolesRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public RolesServiceIMPL(RolesRepository rolesRepository, ModelMapper modelMapper) {
        this.rolesRepository = rolesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RolesDTO> getAll(Pageable pageable) {
        List<RolesDTO> results = new ArrayList<>();
        List<RolesEntity> roleEntities = rolesRepository.findAll(pageable).getContent();
        for (RolesEntity item: roleEntities
        ) {
            RolesDTO dto = modelMapper.map(item,RolesDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<RolesDTO> getByRoleName(String rolename, Pageable pageable) {
        List<RolesDTO> results = new ArrayList<>();
        List<RolesEntity> roleEntities = rolesRepository.findByRolename(rolename,pageable);
        for (RolesEntity item: roleEntities
        ) {
            RolesDTO dto = modelMapper.map(item,RolesDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) rolesRepository.count();
    }

    @Override
    public RolesDTO getByRoleid(Integer roleid) {
        try {
            RolesEntity role = rolesRepository.findByRoleid(roleid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + roleid));
            return modelMapper.map(role,RolesDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteByRoleid(Integer roleid) {
        rolesRepository.deleteByRoleid(roleid);
    }

    @Override
    public void createRoles(RolesDTO rolesDTO) {
        if (rolesDTO != null) {
            RolesEntity roles =  modelMapper.map(rolesDTO, RolesEntity.class);
            System.out.println("| "+roles.getRolename());
            if (roles != null ) {
                rolesRepository.save(roles);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void updateRoles(RolesDTO rolesDTO) {
        RolesEntity existingRole  = rolesRepository.findByRoleid(rolesDTO.getRoleid())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(rolesDTO, existingRole);
        rolesRepository.save(existingRole);
    }
}
