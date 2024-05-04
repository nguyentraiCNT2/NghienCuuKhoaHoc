package com.KnowledgeHubbackend.dto;

import java.sql.Date;

public class FavoriteListDTO {
    private Integer favoriteListid;
    private DocumentDTO documentid;
    private UsersDTO userid;
    private Date dateAdd;

    public Integer getFavoriteListid() {
        return favoriteListid;
    }

    public void setFavoriteListid(Integer favoriteListid) {
        this.favoriteListid = favoriteListid;
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

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }
}
