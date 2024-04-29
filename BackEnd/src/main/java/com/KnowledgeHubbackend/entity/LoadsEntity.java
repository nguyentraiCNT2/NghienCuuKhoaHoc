package com.KnowledgeHubbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "Loads")
public class LoadsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loadid")
    private Integer loadid;
    @ManyToOne
    @JoinColumn(name = "documentID")
    private DocumentEntity documentID;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity userid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateDown")
    private Date dateDown;

}
