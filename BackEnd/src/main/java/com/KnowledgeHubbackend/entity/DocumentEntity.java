package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Document")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documentid")
    private Integer documentid;
    @Column(name = "documentname", columnDefinition = "NVARCHAR(MAX)")
    private String documentname;
    @Column(name = "fileURL", columnDefinition = "NVARCHAR(MAX)")
    private String fileURL;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private CategoryEntity categoryid;
    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;
    @Column(name = "status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "supplierid")
    private SuppliersEntity supplierid;
    @Column(name = "views")
    private Integer views;
    @Column(name = "countDownload")
    private Integer countDownload;
    @ManyToOne
    @JoinColumn(name = "authorID")
    private AuthorEntity authorID;
    @ManyToOne
    @JoinColumn(name = "publisherid")
    private PublishersEntity publisherid;
    @ManyToOne
    @JoinColumn(name = "genreid")
    private GenresEntity genreid;
    @Column(name = "documentthumbnail", columnDefinition = "NVARCHAR(MAX)")
    private String documentthumbnail;
    @Column(name = "timeadd")
    private Date timeadd;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;
    @Column(name = "timeupdate")
    private Date timeupdate;
    @ManyToOne
    @JoinColumn(name = "updaterid")
    private UsersEntity updaterid;

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

    public CategoryEntity getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(CategoryEntity categoryid) {
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

    public SuppliersEntity getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(SuppliersEntity supplierid) {
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

    public AuthorEntity getAuthorID() {
        return authorID;
    }

    public void setAuthorID(AuthorEntity authorID) {
        this.authorID = authorID;
    }

    public PublishersEntity getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(PublishersEntity publisherid) {
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

    public UsersEntity getUserid() {
        return userid;
    }

    public void setUserid(UsersEntity userid) {
        this.userid = userid;
    }

    public Date getTimeupdate() {
        return timeupdate;
    }

    public void setTimeupdate(Date timeupdate) {
        this.timeupdate = timeupdate;
    }

    public UsersEntity getUpdaterid() {
        return updaterid;
    }

    public void setUpdaterid(UsersEntity updaterid) {
        this.updaterid = updaterid;
    }

    public GenresEntity getGenreid() {
        return genreid;
    }

    public void setGenreid(GenresEntity genreid) {
        this.genreid = genreid;
    }
}
