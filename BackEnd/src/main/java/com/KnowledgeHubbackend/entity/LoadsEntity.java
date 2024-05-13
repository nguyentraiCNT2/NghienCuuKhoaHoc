package com.KnowledgeHubbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "Loads")
public class LoadsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loadid")
    private Integer loadid;
    @ManyToOne
    @JoinColumn(name = "documentID")
    private DocumentEntity documentID;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateDown")
    private Date dateDown;

    public Integer getLoadid() {
        return loadid;
    }

    public void setLoadid(Integer loadid) {
        this.loadid = loadid;
    }

    public DocumentEntity getDocumentID() {
        return documentID;
    }

    public void setDocumentID(DocumentEntity documentID) {
        this.documentID = documentID;
    }

    public UsersEntity getUserid() {
        return userid;
    }

    public void setUserid(UsersEntity userid) {
        this.userid = userid;
    }

    public Date getDateDown() {
        return dateDown;
    }

    public void setDateDown(Date dateDown) {
        this.dateDown = dateDown;
    }
}
