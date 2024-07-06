package com.KnowledgeHubbackend.dto;

import com.KnowledgeHubbackend.entity.GenresEntity;

public class CategoryDTO {
    private Integer categoryid;
    private String categoryname;
    private String description;
    private GenresDTO genres;

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

    public GenresDTO getGenres() {
        return genres;
    }

    public void setGenres(GenresDTO genres) {
        this.genres = genres;
    }
}
