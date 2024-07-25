package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.UsersDTO;

public interface LoginService {
    UsersDTO loginUser(String username, String password);
    UsersDTO loginCTV(String username, String password);
    UsersDTO loginAdmin(String username, String password);
    UsersDTO ComfirmEmail(String code, String userid);
    void SignUp(UsersDTO userDTO);
    UsersDTO getUserById(String token);
    UsersDTO CheckUser(String username, String email);
    UsersDTO forgotPassword(UsersDTO dto);
}
