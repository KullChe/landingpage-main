package com.attech.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlockArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="areaCode")
    private String areaCode;
    @Column(name="image")
    private String image;
    @Column(name = "textTitle")
    private String textTitle;
    @Column(name = "textContent")
    private String textContent;



}
