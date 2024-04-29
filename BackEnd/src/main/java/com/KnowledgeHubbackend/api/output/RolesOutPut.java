package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.dto.UsersDTO;

import java.util.ArrayList;
import java.util.List;

public class RolesOutPut {
    private int page;
    private int totalPage;
    private List<RolesDTO> listResult = new ArrayList<>();

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

    public List<RolesDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<RolesDTO> listResult) {
        this.listResult = listResult;
    }
}
