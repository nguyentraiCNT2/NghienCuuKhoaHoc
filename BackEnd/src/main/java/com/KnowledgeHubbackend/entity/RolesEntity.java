package com.KnowledgeHubbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Integer roleid;
    @Column(name = "rolename",columnDefinition = "NVARCHAR(MAX)")
    private String  rolename;
    @Column(name = "description",columnDefinition = "NVARCHAR(MAX)")
    private String description;

}
