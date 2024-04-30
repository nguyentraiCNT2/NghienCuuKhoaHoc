package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.MenuDTO;
import com.KnowledgeHubbackend.dto.RolesDTO;
import com.KnowledgeHubbackend.entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuOutPut {
    private int page;
    private int totalPage;
    private List<MenuDTO> listResult = new ArrayList<>();

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

    public List<MenuDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<MenuDTO> listResult) {
        this.listResult = listResult;
    }
}
