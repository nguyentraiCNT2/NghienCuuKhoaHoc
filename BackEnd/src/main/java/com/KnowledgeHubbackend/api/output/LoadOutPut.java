package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.HistoryDTO;
import com.KnowledgeHubbackend.dto.LoadsDTO;

import java.util.ArrayList;
import java.util.List;

public class LoadOutPut {
    private int page;
    private int totalPage;
    private List<LoadsDTO> listResult = new ArrayList<>();

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

    public List<LoadsDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<LoadsDTO> listResult) {
        this.listResult = listResult;
    }
}
