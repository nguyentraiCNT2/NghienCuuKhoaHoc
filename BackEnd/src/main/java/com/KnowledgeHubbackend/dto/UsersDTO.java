package com.KnowledgeHubbackend.dto;

import java.sql.Date;
public class UsersDTO {
    private String userid;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date birthofday;
    private Boolean gender;
    private Boolean status;
    private String avartaURL;

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

    public String getAvartaURL() {
        return avartaURL;
    }

    public void setAvartaURL(String avartaURL) {
        this.avartaURL = avartaURL;
    }
}
