package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RolesService {
    List<RolesDTO> getAll(Pageable pageable);
    List<RolesDTO> getByRoleName(String rolename,Pageable pageable);
    int totalItem();
    RolesDTO getByRoleid(Integer roleid);
    void deleteByRoleid(Integer roleid);
    void createRoles(RolesDTO rolesDTO);

    void updateRoles(RolesDTO rolesDTO);
}
