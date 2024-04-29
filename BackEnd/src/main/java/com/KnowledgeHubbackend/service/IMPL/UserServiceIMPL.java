package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.algorithm.RandomId;
import com.KnowledgeHubbackend.dto.UsersDTO;
import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.UserRoleEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.RolesRepository;
import com.KnowledgeHubbackend.repository.UserRepository;
import com.KnowledgeHubbackend.repository.UserRoleRepository;
import com.KnowledgeHubbackend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private  final UserRepository userRepository;
    @Autowired
    private final RolesRepository rolesRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    public UserServiceIMPL(UserRepository userRepository, RolesRepository rolesRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.userRoleRepository = userRoleRepository;
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
    public List<UsersDTO> getByUserName(String username, Pageable pageable) {
        List<UsersDTO> results = new ArrayList<>();
        List<UsersEntity> userEntities = userRepository.findByUsername(username,pageable);
        for (UsersEntity item: userEntities
        ) {
            UsersDTO userDTO = modelMapper.map(item,UsersDTO.class);
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public List<UsersDTO> getByUserRole(Integer roleid, Pageable pageable) {
        List<UsersDTO> results = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        List<UsersEntity> userEntities = userRepository.findAll(pageable).getContent();
        RolesEntity existingRole  = rolesRepository.findByRoleid(roleid)
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu quyen han"));
        List<UserRoleEntity> userroleEntities = userRoleRepository.findByRoleid(existingRole);
        for (UsersEntity item: userEntities
        ) {
            for (UserRoleEntity userrole:userroleEntities
                 ) {
                if (userrole.getUserid() == item){
                    UsersDTO userDTO = modelMapper.map(item,UsersDTO.class);
                    results.add(userDTO);
                }
            }

        }
        return results;
    }

    @Override
    public List<UsersDTO> getByEmail(String email, Pageable pageable) {
        List<UsersDTO> results = new ArrayList<>();
        List<UsersEntity> userEntities = userRepository.findByEmail(email,pageable);
        for (UsersEntity item: userEntities
        ) {
            UsersDTO userDTO = modelMapper.map(item,UsersDTO.class);
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public List<UsersDTO> getByPhone(String phone, Pageable pageable) {
        List<UsersDTO> results = new ArrayList<>();
        List<UsersEntity> userEntities = userRepository.findByPhone(phone,pageable);
        for (UsersEntity item: userEntities
        ) {
            UsersDTO userDTO = modelMapper.map(item,UsersDTO.class);
            results.add(userDTO);
        }
        return results;
    }


    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

    @Override
    public UsersDTO getByUserid(String userid) {
        try {
            UsersEntity user = userRepository.findByUserid(userid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + userid));
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
    public void createUser(UsersDTO userDTO, Integer roleid) {
        if ( userDTO != null) {
            UsersEntity user =  modelMapper.map(userDTO, UsersEntity.class);
            user.setUserid(RandomId.generateRandomId(20));
            RolesEntity roles = rolesRepository.findByRoleid(roleid).orElse(null);
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRoleid(roles);
            userRoleEntity.setUserid(user);
            if (user != null && userRoleEntity!= null) {
                userRepository.save(user);
                userRoleRepository.save(userRoleEntity);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void updateUser(UsersDTO userDTO) {
        UsersEntity existingUser  = userRepository.findByUserid(userDTO.getUserid())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(userDTO, existingUser);
        userRepository.save(existingUser);
    }
}
