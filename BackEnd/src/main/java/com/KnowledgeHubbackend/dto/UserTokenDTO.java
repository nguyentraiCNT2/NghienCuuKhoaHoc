package com.KnowledgeHubbackend.dto;

import com.KnowledgeHubbackend.entity.UsersEntity;
import jakarta.persistence.*;

public class UserTokenDTO {
    private int tokenid;
    private String token;
    private UsersDTO users;

    public int getTokenid() {
        return tokenid;
    }

    public void setTokenid(int tokenid) {
        this.tokenid = tokenid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsersDTO getUsers() {
        return users;
    }

    public void setUsers(UsersDTO users) {
        this.users = users;
    }
}
