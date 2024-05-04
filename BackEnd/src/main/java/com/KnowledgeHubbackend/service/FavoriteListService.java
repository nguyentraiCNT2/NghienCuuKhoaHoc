package com.KnowledgeHubbackend.service;

import com.KnowledgeHubbackend.dto.FavoriteListDTO;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface FavoriteListService {
    List<FavoriteListDTO> getAll(Pageable pageable);

    FavoriteListDTO getByFavoriteListid(Integer favoriteListid);

    List<FavoriteListDTO> getByDateAdd(Date dateAdd, Pageable pageable);

    List<FavoriteListDTO> getByDocumentid(Integer documentid, Pageable pageable);

    List<FavoriteListDTO> getByUserid(String userid, Pageable pageable);

    int totalItem();

    void deleteByFavoriteListid(Integer favoriteListid);

    void createFavoriteList(FavoriteListDTO favoriteListDTO);

    void updateFavoriteList(FavoriteListDTO favoriteListDTO);
}
