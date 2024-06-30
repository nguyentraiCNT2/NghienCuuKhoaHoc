package com.KnowledgeHubbackend.dto;

import com.KnowledgeHubbackend.entity.UsersEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

public class DocumentDTO {
    private Integer documentid;
    private String documentname;
    private String fileURL;
    private CategoryDTO categoryid;
    private String description;
    private Boolean status;
    private SuppliersDTO supplierid;
    private Integer views;
    private Integer countDownload;
    private AuthorDTO authorID;
    private PublishersDTO publisherid;
    private String documentthumbnail;
    private Date timeadd;
    private UsersDTO userid;
    private Date timeupdate;
    private UsersDTO updaterid;

    public Integer getDocumentid() {
        return documentid;
    }

    public void setDocumentid(Integer documentid) {
        this.documentid = documentid;
    }

    public String getDocumentname() {
        return documentname;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public CategoryDTO getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(CategoryDTO categoryid) {
        this.categoryid = categoryid;
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

    public SuppliersDTO getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(SuppliersDTO supplierid) {
        this.supplierid = supplierid;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCountDownload() {
        return countDownload;
    }

    public void setCountDownload(Integer countDownload) {
        this.countDownload = countDownload;
    }

    public AuthorDTO getAuthorID() {
        return authorID;
    }

    public void setAuthorID(AuthorDTO authorID) {
        this.authorID = authorID;
    }

    public PublishersDTO getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(PublishersDTO publisherid) {
        this.publisherid = publisherid;
    }


    public String getDocumentthumbnail() {
        return documentthumbnail;
    }

    public void setDocumentthumbnail(String documentthumbnail) {
        this.documentthumbnail = documentthumbnail;
    }

    public Date getTimeadd() {
        return timeadd;
    }

    public void setTimeadd(Date timeadd) {
        this.timeadd = timeadd;
    }

    public UsersDTO getUserid() {
        return userid;
    }

    public void setUserid(UsersDTO userid) {
        this.userid = userid;
    }

    public Date getTimeupdate() {
        return timeupdate;
    }

    public void setTimeupdate(Date timeupdate) {
        this.timeupdate = timeupdate;
    }

    public UsersDTO getUpdaterid() {
        return updaterid;
    }

    public void setUpdaterid(UsersDTO updaterid) {
        this.updaterid = updaterid;
    }
}
