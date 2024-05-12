package com.KnowledgeHubbackend.api.output;

import com.KnowledgeHubbackend.dto.HistoryDTO;
import com.KnowledgeHubbackend.dto.NotificationsDTO;

import java.util.ArrayList;
import java.util.List;

public class NotificationsOutPut {
    private int page;
    private int totalPage;
    private List<NotificationsDTO> listResult = new ArrayList<>();

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

    public List<NotificationsDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<NotificationsDTO> listResult) {
        this.listResult = listResult;
    }
}
