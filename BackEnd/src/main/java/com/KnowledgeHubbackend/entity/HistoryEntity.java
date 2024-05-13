package com.KnowledgeHubbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "History")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historyid")
    private Integer historyid;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;
    @ManyToOne
    @JoinColumn(name = "documentid")
    private DocumentEntity documentid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateupdate")
    private Date dateupdate;
    @Column(name = "description",columnDefinition = "NVARCHAR(MAX)")
   private String description;
    @Column(name = "status")
    private Boolean status;
    public Integer getHistoryid() {
        return historyid;
    }

    public void setHistoryid(Integer historyid) {
        this.historyid = historyid;
    }

    public UsersEntity getUserid() {
        return userid;
    }

    public void setUserid(UsersEntity userid) {
        this.userid = userid;
    }

    public DocumentEntity getDocumentid() {
        return documentid;
    }

    public void setDocumentid(DocumentEntity documentid) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
