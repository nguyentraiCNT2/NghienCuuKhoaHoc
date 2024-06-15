package com.KnowledgeHubbackend.dto;

public class MenuDTO {
    private Integer menuid;
    private String menuName;
    private String menuLink;
    private MenuDTO parentID;

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

    public MenuDTO getParentID() {
        return parentID;
    }

    public void setParentID(MenuDTO parentID) {
        this.parentID = parentID;
    }
}
