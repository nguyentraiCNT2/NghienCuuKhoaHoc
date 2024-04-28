package com.KnowledgeHubbackend.dto;

import java.sql.Date;

public class DownloaderDTO {
    private Integer downID;
    private String downName;
    private String downEmail;
    private String downPhone;
    private Date dateDown;
    private LoadsDTO loadid;

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

    public LoadsDTO getLoadid() {
        return loadid;
    }

    public void setLoadid(LoadsDTO loadid) {
        this.loadid = loadid;
    }
}
