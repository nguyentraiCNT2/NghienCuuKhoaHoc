package com.KnowledgeHubbackend.dto;

public class FavoriteListDTO {
    private Integer favoriteListid;
    private String favoriteListName;
    private String description;
    private DocumentDTO documentid;
    private UsersDTO userid;

    public Integer getFavoriteListid() {
        return favoriteListid;
    }

    public void setFavoriteListid(Integer favoriteListid) {
        this.favoriteListid = favoriteListid;
    }

    public String getFavoriteListName() {
        return favoriteListName;
    }

    public void setFavoriteListName(String favoriteListName) {
        this.favoriteListName = favoriteListName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
