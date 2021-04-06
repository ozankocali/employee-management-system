package com.ozeeesoftware.employeemanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @ManyToOne
    private Department department;

    @Column(name="age")
    private short age;

    @Column(name = "salary")
    private double salary;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "imageId")
    private ImageModel profileImage;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, Department department, short age, double salary, String phone, String email, ImageModel profileImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.age = age;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.profileImage = profileImage;
    }



}
