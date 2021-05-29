package com.ozeeesoftware.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="departments")
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "max_salary")
    private double maxSalary;

    @Column(name = "min_salary")
    private double minSalary;

    @Column(name="description")
    private String jobDescription;

    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Employee> employees;


    public Department(Long id, String departmentName, double maxSalary, double minSalary, String jobDescription, String phone) {
        this.id = id;
        this.departmentName = departmentName;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.jobDescription = jobDescription;
        this.phone = phone;
    }
}
