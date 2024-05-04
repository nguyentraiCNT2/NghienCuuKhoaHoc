package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.AuthorDTO;
import com.KnowledgeHubbackend.dto.FavoriteListDTO;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListOutPut {
    private int page;
    private int totalPage;
    private List<FavoriteListDTO> listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<FavoriteListDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<FavoriteListDTO> listResult) {
        this.listResult = listResult;
    }
}
