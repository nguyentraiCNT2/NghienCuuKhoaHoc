package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.FavoriteListDTO;
import com.KnowledgeHubbackend.dto.GenresDTO;

import java.util.ArrayList;
import java.util.List;

public class GenresOUTPUT {
    private int page;
    private int totalPage;
    private List<GenresDTO> listResult = new ArrayList<>();

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

    public List<GenresDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<GenresDTO> listResult) {
        this.listResult = listResult;
    }
}
