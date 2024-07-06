package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.UserTokenDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.UserTokenEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;

public interface UserTokenService {
    void  createToken(String token, UsersDTO dto);
    void deleteTokenByUsers(UsersDTO dto);
    UserTokenDTO getTokenByUsers(UsersDTO dto);
    UserTokenDTO getTokenByToken(String token);
    void updateToken(Integer tokenid,String token, UsersDTO dto);
}
