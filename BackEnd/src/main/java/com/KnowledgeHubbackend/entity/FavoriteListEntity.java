package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "FavoriteList")
public class FavoriteListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favoriteListid")
    private Integer favoriteListid;
    @ManyToOne
    @JoinColumn(name = "documentid")
    private DocumentEntity documentid;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;
    @Column(name = "dateAdd")
    private Date dateAdd;
    public Integer getFavoriteListid() {
        return favoriteListid;
    }

    public void setFavoriteListid(Integer favoriteListid) {
        this.favoriteListid = favoriteListid;
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

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }
}
