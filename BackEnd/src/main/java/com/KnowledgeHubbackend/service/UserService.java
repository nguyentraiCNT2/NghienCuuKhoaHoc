package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.UsersDTO;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserService {
    List<UsersDTO> getAll(Pageable pageable);
    List<UsersDTO> getByUserName(Pageable pageable);
    List<UsersDTO> getByEmail(Pageable pageable);
    List<UsersDTO> getByPhone(Pageable pageable);
    int totalItem();
    UsersDTO getByUserid(String usersid);
    void deleteByUserid(String userid);
    void createUser(UsersDTO userDTO);

    void updateUser(UsersDTO userDTO);

}
