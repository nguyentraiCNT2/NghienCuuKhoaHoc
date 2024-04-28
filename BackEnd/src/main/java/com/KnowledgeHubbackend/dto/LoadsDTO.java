package com.KnowledgeHubbackend.dto;

import java.sql.Date;

public class LoadsDTO {
    private Integer loadid;
    private DocumentDTO documentID;
    private UsersDTO userid;
    private Date dateDown;

    public Integer getLoadid() {
        return loadid;
    }

    public void setLoadid(Integer loadid) {
        this.loadid = loadid;
    }

    public DocumentDTO getDocumentID() {
        return documentID;
    }

    public void setDocumentID(DocumentDTO documentID) {
        this.documentID = documentID;
    }

    public UsersDTO getUserid() {
        return userid;
    }

    public void setUserid(UsersDTO userid) {
        this.userid = userid;
    }

    public Date getDateDown() {
        return dateDown;
    }

    public void setDateDown(Date dateDown) {
        this.dateDown = dateDown;
    }
}
