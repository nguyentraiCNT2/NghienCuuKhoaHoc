package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.UsersDTO;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserService {
    List<UsersDTO> getAll(Pageable pageable);
    List<UsersDTO> getByUserName(String username,Pageable pageable);
    List<UsersDTO> getByUserRole(Integer roleid,Pageable pageable);
    List<UsersDTO> getByEmail(String email,Pageable pageable);
    List<UsersDTO> getByPhone(String phone,Pageable pageable);
    int totalItem();
    UsersDTO getByUserid(String userid);
    void deleteByUserid(String userid);
    void createUser(UsersDTO userDTO, Integer roleid);

    void updateUser(UsersDTO userDTO);

}
