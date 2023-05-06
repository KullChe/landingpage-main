package com.attech.model.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "infowebsite")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Websites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "websitename")
    private String name;
    @Column(name = "createddate")
    private Date dateCreate;
    @Column(name = "updateddate")
    private Date lastEdited;
    @Column(name = "aliaswebsite")
    private String aliasWebsite;
    @Column(name = "nameserver")
    private String nameServer;
    @Column(name = "domain")
    private String domain;
    @Column(name="tokenWebsite")
    private String tokenWebsite;
}
