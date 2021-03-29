package com.ozeeesoftware.employeemanagement.service;

import com.ozeeesoftware.employeemanagement.model.Department;
import org.springframework.http.ResponseEntity;



public interface DepartmentService {
    ResponseEntity getAllDepartments();
    ResponseEntity getDepartmentById(long id);
    ResponseEntity createDepartment(Department department);
    ResponseEntity updateDepartment(Department department);
    ResponseEntity deleteDepartment(Department department);
    ResponseEntity deleteDepartmentById(long id);
}
