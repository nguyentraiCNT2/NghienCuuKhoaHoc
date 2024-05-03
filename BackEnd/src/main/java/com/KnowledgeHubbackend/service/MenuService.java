package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.MenuDTO;
import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.entity.MenuEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuService {
    List<MenuDTO> getAll(Pageable pageable);
    @Query("select a from  MenuEntity a where a.menuName like % :menuName %")
    List<MenuDTO> getByMenuName(String menuName,Pageable pageable);
    int totalItem();
    MenuDTO getByMenuid(Integer menuid);
    void deleteByMenuid(Integer menuid);
    void createMenu(MenuDTO menuDTO);

    void updateMenu(MenuDTO menuDTO);

}
