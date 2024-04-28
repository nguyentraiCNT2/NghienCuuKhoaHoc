package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "Downloader")
public class DownloaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "downID")
    private Integer downID;
    @Column(name = "downName",columnDefinition = "NVARCHAR(MAX)")
    private String downName;
    @Column(name = "downEmail",columnDefinition = "NVARCHAR(MAX)")
    private String downEmail;
    @Column(name = "downPhone",columnDefinition = "NVARCHAR(MAX)")
    private String downPhone;
    @Column(name = "dateDown")
    private Date dateDown;
    @ManyToOne
    @JoinColumn(name = "loadid")
    private LoadsEntity loadid;

    public Integer getDownID() {
        return downID;
    }

    public void setDownID(Integer downID) {
        this.downID = downID;
    }

    public String getDownName() {
        return downName;
    }

    public void setDownName(String downName) {
        this.downName = downName;
    }

    public String getDownEmail() {
        return downEmail;
    }

    public void setDownEmail(String downEmail) {
        this.downEmail = downEmail;
    }

    public String getDownPhone() {
        return downPhone;
    }

    public void setDownPhone(String downPhone) {
        this.downPhone = downPhone;
    }

    public Date getDateDown() {
        return dateDown;
    }

    public void setDateDown(Date dateDown) {
        this.dateDown = dateDown;
    }

    public LoadsEntity getLoadid() {
        return loadid;
    }

    public void setLoadid(LoadsEntity loadid) {
        this.loadid = loadid;
    }
}
