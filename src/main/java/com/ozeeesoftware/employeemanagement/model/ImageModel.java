package com.ozeeesoftware.employeemanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "imageName")
    private String name;
    @Column(name = "imageUrl")
    private String url;

}
