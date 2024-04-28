package com.KnowledgeHubbackend.dto;

public class UserRoleDTO {
    private Integer userroleid;
    private UsersDTO userid;
    private RolesDTO roleid;

    public Integer getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(Integer userroleid) {
        this.userroleid = userroleid;
    }

    public UsersDTO getUserid() {
        return userid;
    }

    public void setUserid(UsersDTO userid) {
        this.userid = userid;
    }

    public RolesDTO getRoleid() {
        return roleid;
    }

    public void setRoleid(RolesDTO roleid) {
        this.roleid = roleid;
    }
}
