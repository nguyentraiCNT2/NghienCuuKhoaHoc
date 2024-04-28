package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    public UserServiceIMPL(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UsersDTO> getAll(Pageable pageable) {
        List<UsersDTO> results = new ArrayList<>();
        List<UsersEntity> userEntities = userRepository.findAll(pageable).getContent();
        for (UsersEntity item: userEntities
        ) {
            UsersDTO userDTO = modelMapper.map(item,UsersDTO.class);
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public List<UsersDTO> getByUserName(Pageable pageable) {
        return null;
    }

    @Override
    public List<UsersDTO> getByEmail(Pageable pageable) {
        return null;
    }

    @Override
    public List<UsersDTO> getByPhone(Pageable pageable) {
        return null;
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

    @Override
    public UsersDTO getByUserid(String usersid) {
        try {
            UsersEntity user = userRepository.findByUserid(usersid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + usersid));
            return modelMapper.map(user,UsersDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteByUserid(String userid) {
        userRepository.deleteByUserid(userid);
    }

    @Override
    public void createUser(UsersDTO userDTO) {
        if ( userDTO != null) {

            UsersEntity user =  modelMapper.map(userDTO, UsersEntity.class);
            if (user != null) {
                userRepository.save(user);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateUser(UsersDTO userDTO) {

    }
}
