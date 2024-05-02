package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.CategoryDTO;
import com.KnowledgeHubbackend.dto.DocumentDTO;

import java.util.ArrayList;
import java.util.List;

public class DocumentOutput {
    private int page;
    private int totalPage;
    private List<DocumentDTO> listResult = new ArrayList<>();

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

    public List<DocumentDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<DocumentDTO> listResult) {
        this.listResult = listResult;
    }
}
