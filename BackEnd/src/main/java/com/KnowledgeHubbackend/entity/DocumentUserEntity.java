package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "document_user")
public class DocumentUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_user_id")
    private Integer documentuserid;
    @ManyToOne
    @JoinColumn(name = "document_id")
    private DocumentEntity documentid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity userid;

    public Integer getDocumentuserid() {
        return documentuserid;
    }

    public void setDocumentuserid(Integer documentuserid) {
        this.documentuserid = documentuserid;
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
