package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MenuID")
    private Integer menuid;
    @Column(name = "MenuName",columnDefinition = "NVARCHAR(MAX)")
    private String menuName;
    @Column(name = "MenuLink",columnDefinition = "NVARCHAR(MAX)")
    private String menuLink;
    @ManyToOne
    @JoinColumn(name = "parentID")
    private MenuEntity parentID;

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public MenuEntity getParentID() {
        return parentID;
    }

    public void setParentID(MenuEntity parentID) {
        this.parentID = parentID;
    }
}
