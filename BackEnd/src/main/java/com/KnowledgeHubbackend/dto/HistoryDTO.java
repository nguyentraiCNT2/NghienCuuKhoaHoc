package com.KnowledgeHubbackend.dto;

import java.sql.Date;

public class HistoryDTO {
    private Integer historyid;
    private UsersDTO userid;
    private DocumentDTO documentid;
    private Date dateupdate;
    private String description;
    public Integer getHistoryid() {
        return historyid;
    }

    public void setHistoryid(Integer historyid) {
        this.historyid = historyid;
    }

    public UsersDTO getUserid() {
        return userid;
    }

    public void setUserid(UsersDTO userid) {
        this.userid = userid;
    }

    public DocumentDTO getDocumentid() {
        return documentid;
    }

    public void setDocumentid(DocumentDTO documentid) {
        this.documentid = documentid;
    }

    public Date getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(Date dateupdate) {
        this.dateupdate = dateupdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
