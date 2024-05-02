package com.KnowledgeHubbackend.repository;

import com.KnowledgeHubbackend.entity.MenuEntity;
import com.KnowledgeHubbackend.entity.RolesEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
    Optional<MenuEntity> findByMenuid(Integer menuid);
    List<MenuEntity> findByMenuName(String menuName);
    List<MenuEntity> findByMenuName(String menuName, Pageable pageable);
    List<MenuEntity> findByParentID(Integer parentID, Pageable pageable);
    void deleteByMenuid(Integer menuid);
    MenuEntity saveAndFlush(MenuEntity menuEntity);
}