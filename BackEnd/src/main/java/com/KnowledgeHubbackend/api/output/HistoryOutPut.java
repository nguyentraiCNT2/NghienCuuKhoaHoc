package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.FavoriteListDTO;
import com.KnowledgeHubbackend.dto.HistoryDTO;

import java.util.ArrayList;
import java.util.List;

public class HistoryOutPut {
    private int page;
    private int totalPage;
    private List<HistoryDTO> listResult = new ArrayList<>();

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

    public List<HistoryDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<HistoryDTO> listResult) {
        this.listResult = listResult;
    }
}
