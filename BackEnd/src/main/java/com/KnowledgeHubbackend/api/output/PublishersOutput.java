package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.PublishersDTO;

import java.util.ArrayList;
import java.util.List;

public class PublishersOutput {
    private int page;
    private int totalPage;
    private List<PublishersDTO> listResult = new ArrayList<>();

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

    public List<PublishersDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<PublishersDTO> listResult) {
        this.listResult = listResult;
    }
}
