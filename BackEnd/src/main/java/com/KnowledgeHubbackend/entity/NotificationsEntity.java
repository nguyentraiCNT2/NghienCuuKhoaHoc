package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "Notifications")
public class NotificationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationid")
    private Integer notificationid;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;
    @ManyToOne
    @JoinColumn(name = "documentid")
    private DocumentEntity documentid;
    @Column(name = "dateadd")
    private Date dateadd;
    @Column(name = "description",columnDefinition = "NVARCHAR(MAX)")
    private String description;

    public Integer getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(Integer notificationid) {
        this.notificationid = notificationid;
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
