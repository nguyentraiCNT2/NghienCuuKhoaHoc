package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private Integer categoryid;
    @Column(name = "categoryname",columnDefinition = "NVARCHAR(MAX)")
    private String categoryname;
    @Column(name = "description",columnDefinition = "NVARCHAR(MAX)")
    private String description;
    @ManyToOne
    @JoinColumn(name = "genreid")
    private GenresEntity genres;

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenresEntity getGenres() {
        return genres;
    }

    public void setGenres(GenresEntity genres) {
        this.genres = genres;
    }
}
