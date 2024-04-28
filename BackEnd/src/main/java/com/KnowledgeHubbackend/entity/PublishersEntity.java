package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Publishers")
public class PublishersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisherid")
    private Integer publisherid;
    @Column(name = "publishername",columnDefinition = "NVARCHAR(MAX)")
    private String publishername;
    @Column(name = "description",columnDefinition = "NVARCHAR(MAX)")
    private String description;

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
