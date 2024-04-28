package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FavoriteList")
public class FavoriteListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favoriteListid")
    private Integer favoriteListid;
    @Column(name = "favoriteListName",columnDefinition = "NVARCHAR(MAX)")
    private String favoriteListName;
    @Column(name = "description",columnDefinition = "NVARCHAR(MAX)")
    private String description;
    @ManyToOne
    @JoinColumn(name = "documentid")
    private DocumentEntity documentid;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;

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

    public DocumentEntity getDocumentid() {
        return documentid;
    }

    public void setDocumentid(DocumentEntity documentid) {
        this.documentid = documentid;
    }

    public UsersEntity getUserid() {
        return userid;
    }

    public void setUserid(UsersEntity userid) {
        this.userid = userid;
    }
}
