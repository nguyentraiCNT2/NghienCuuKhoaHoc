package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.UserTokenDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.UserTokenEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.repository.UserTokenRepository;
import com.KnowledgeHubbackend.service.UserTokenService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTokenServiceIMPL implements UserTokenService {
    @Autowired
    private final UserTokenRepository userTokenRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;


    public UserTokenServiceIMPL(UserTokenRepository userTokenRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.userTokenRepository = userTokenRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void createToken(String token, UsersDTO dto) {
        if (token != null && dto != null) {

            UsersEntity user = userRepository.findByUserid(dto.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + dto.getUserid()));
            List<UserTokenEntity> usertokenList = userTokenRepository.findByUsers(user);

            UserTokenEntity userTokenEntity = usertokenList.get(0);
            userTokenEntity.setUsers(user);
            userTokenEntity.setToken(token);
            userTokenRepository.save(userTokenEntity);
        }
    }

    @Override
    public void deleteTokenByUsers(UsersDTO dto) {
        if (dto != null) {
            UsersEntity user = userRepository.findByUserid(dto.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + dto.getUserid()));
            List<UserTokenEntity> usertokenList = userTokenRepository.findByUsers(user);
            if (usertokenList.size() > 0) {
                userTokenRepository.deleteByUsers(user);
            }
        }
    }

    @Override
    public UserTokenDTO getTokenByUsers(UsersDTO dto) {
        try {
            UsersEntity users = userRepository.findByUserid(dto.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + dto.getUserid()));
            List<UserTokenEntity>  userTokenEntity = userTokenRepository.findByUsers(users);
                return modelMapper.map(userTokenEntity.get(0), UserTokenDTO.class);
        }catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public UserTokenDTO getTokenByToken(String token) {
        try {

            List<UserTokenEntity>  userTokenEntity = userTokenRepository.findByToken(token);
            return modelMapper.map(userTokenEntity.get(0), UserTokenDTO.class);
        }catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void updateToken(Integer tokenid, String token, UsersDTO dto) {
        if (tokenid != null && token != null && dto != null) {
            UsersEntity user = userRepository.findByUserid(dto.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + dto.getUserid()));
            List<UserTokenEntity> usertokenList = userTokenRepository.findByUsers(user);
            if (usertokenList.size() > 0) {
                userTokenRepository.deleteByUsers(user);
            }
            UserTokenEntity userTokenEntity = new UserTokenEntity();
            userTokenEntity.setUsers(user);
            userTokenEntity.setToken(token);
            userTokenRepository.save(userTokenEntity);
        }
    }
}
