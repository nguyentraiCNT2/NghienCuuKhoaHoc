package com.KnowledgeHubbackend.service.IMPL;

import com.KnowledgeHubbackend.dto.MenuDTO;
import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.entity.MenuEntity;
import com.KnowledgeHubbackend.entity.RolesEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import com.KnowledgeHubbackend.repository.MenuRepository;
import com.KnowledgeHubbackend.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceIMPL implements MenuService {
    @Autowired
    private final MenuRepository menuRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public MenuServiceIMPL(MenuRepository menuRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MenuDTO> getAll(Pageable pageable) {
        List<MenuDTO> results = new ArrayList<>();
        List<MenuEntity> menuEntities = menuRepository.findAll(pageable).getContent();
        for (MenuEntity item: menuEntities
        ) {
            MenuDTO dto = modelMapper.map(item,MenuDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<MenuDTO> getByMenuName(String menuName, Pageable pageable) {
        List<MenuDTO> results = new ArrayList<>();
        List<MenuEntity> menuEntities = menuRepository.findByMenuName(menuName,pageable);
        for (MenuEntity item: menuEntities
        ) {
            MenuDTO dto = modelMapper.map(item,MenuDTO.class);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<MenuDTO> getByParentID(Integer parentID, Pageable pageable) {
        List<MenuDTO> results = new ArrayList<>();
        if (parentID!= 0) {
            MenuEntity menuEntity= menuRepository.findByMenuid(parentID)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + parentID));
            List<MenuEntity> menuEntities    = menuRepository.findByParentID(menuEntity,pageable);
            for (MenuEntity item: menuEntities
            ) {
                MenuDTO dto = modelMapper.map(item,MenuDTO.class);
                results.add(dto);
            }
        }
    else  {
         List<MenuEntity> menuEntities = menuRepository.findAll(pageable).getContent();
         for (MenuEntity item: menuEntities
         ) {
             if (item.getParentID() == null) {
                 MenuDTO dto = modelMapper.map(item, MenuDTO.class);
                 results.add(dto);
             }
         }
     }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) menuRepository.count();
    }

    @Override
    public MenuDTO getByMenuid(Integer menuid) {
        try {
            MenuEntity menu = menuRepository.findByMenuid(menuid)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + menuid));
            return modelMapper.map(menu,MenuDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteByMenuid(Integer menuid) {
        menuRepository.deleteByMenuid(menuid);
    }

    @Override
    public void createMenu(MenuDTO menuDTO) {
        if (menuDTO != null) {
            MenuEntity menu =  modelMapper.map(menuDTO, MenuEntity.class);
            if (menu != null ) {
                menuRepository.save(menu);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu!");
            }
        }
    }

    @Override
    public void updateMenu(MenuDTO menuDTO) {
        MenuEntity existingMenu  = menuRepository.findByMenuid(menuDTO.getMenuid())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(menuDTO, existingMenu);
        menuRepository.save(existingMenu);
    }
}
