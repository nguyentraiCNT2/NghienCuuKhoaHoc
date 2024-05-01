package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.SuppliersDTO;

import java.util.ArrayList;
import java.util.List;

public class SuppliersOutPut {
    private int page;
    private int totalPage;
    private List<SuppliersDTO> listResult = new ArrayList<>();

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

    public List<SuppliersDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<SuppliersDTO> listResult) {
        this.listResult = listResult;
    }
}
