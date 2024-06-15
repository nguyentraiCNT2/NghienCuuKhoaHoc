package com.KnowledgeHubbackend.dto;

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
    private MenuDTO menuid;
    private String documentthumbnail;

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

    public MenuDTO getMenuid() {
        return menuid;
    }

    public void setMenuid(MenuDTO menuid) {
        this.menuid = menuid;
    }

    public String getDocumentthumbnail() {
        return documentthumbnail;
    }

    public void setDocumentthumbnail(String documentthumbnail) {
        this.documentthumbnail = documentthumbnail;
    }
}
