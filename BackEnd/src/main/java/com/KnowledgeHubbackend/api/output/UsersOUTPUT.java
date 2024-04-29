package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.UsersDTO;

import java.util.ArrayList;
import java.util.List;

public class UsersOUTPUT {
    private int page;
    private int totalPage;
    private List<UsersDTO> listResult = new ArrayList<>();

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

    public List<UsersDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<UsersDTO> listResult) {
        this.listResult = listResult;
    }
}
