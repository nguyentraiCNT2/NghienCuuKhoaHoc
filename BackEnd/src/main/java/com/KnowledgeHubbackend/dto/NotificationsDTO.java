package com.KnowledgeHubbackend.dto;

import java.sql.Date;

public class NotificationsDTO {
    private Integer notificationid;
    private UsersDTO userid;
    private DocumentDTO documentid;
    private Date dateadd;
    private String description;

    public Integer getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public UsersDTO getUserid() {
        return userid;
    }

    public void setUserid(UsersDTO userid) {
        this.userid = userid;
    }

    public DocumentDTO getDocumentid() {
        return documentid;
    }

    public void setDocumentid(DocumentDTO documentid) {
        this.documentid = documentid;
    }

    public Date getDateadd() {
        return dateadd;
    }

    public void setDateadd(Date dateadd) {
        this.dateadd = dateadd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
