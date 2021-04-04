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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "imageId")
    private ImageModel profileImage;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Department department, short age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }


}
