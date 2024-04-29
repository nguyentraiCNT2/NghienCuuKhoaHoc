package com.KnowledgeHubbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Users")
public class UsersEntity {
    @Id
    @Column(name = "userid",columnDefinition = "NVARCHAR(255)")
    private String userid;
    @Column(name = "username",columnDefinition = "NVARCHAR(MAX)")
    private String username;
    @Column(name = "password",columnDefinition = "NVARCHAR(MAX)")
    private String password;
    @Column(name = "email",columnDefinition = "NVARCHAR(MAX)")
    private String email;
    @Column(name = "phone",columnDefinition = "NVARCHAR(MAX)")
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthofday")
    private Date birthofday;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "status")
    private Boolean status;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthofday() {
        return birthofday;
    }

    public void setBirthofday(Date birthofday) {
        this.birthofday = birthofday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
