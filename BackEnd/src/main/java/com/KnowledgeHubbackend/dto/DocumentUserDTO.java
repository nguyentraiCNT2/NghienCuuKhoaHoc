package com.KnowledgeHubbackend.dto;

import com.KnowledgeHubbackend.entity.DocumentEntity;
import com.KnowledgeHubbackend.entity.UsersEntity;
import jakarta.persistence.*;

public class DocumentUserDTO {
    private Integer documentuserid;
    private DocumentDTO documentid;
    private UsersDTO userid;

    public Integer getDocumentuserid() {
        return documentuserid;
    }

    public void setDocumentuserid(Integer documentuserid) {
        this.documentuserid = documentuserid;
    }

    public DocumentDTO getDocumentid() {
        return documentid;
    }

    public void setDocumentid(DocumentDTO documentid) {
        this.documentid = documentid;
    }

    public UsersDTO getUserid() {
        return userid;
    }

    public void setUserid(UsersDTO userid) {
        this.userid = userid;
    }
}
