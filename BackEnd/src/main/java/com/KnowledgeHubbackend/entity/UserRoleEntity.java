package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserRole")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userroleid")
    private Integer userroleid;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;
    @ManyToOne
    @JoinColumn(name = "roleid")
    private RolesEntity roleid;

    public Integer getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(Integer userroleid) {
        this.userroleid = userroleid;
    }

    public UsersEntity getUserid() {
        return userid;
    }

    public void setUserid(UsersEntity userid) {
        this.userid = userid;
    }

    public RolesEntity getRoleid() {
        return roleid;
    }

    public void setRoleid(RolesEntity roleid) {
        this.roleid = roleid;
    }
}
