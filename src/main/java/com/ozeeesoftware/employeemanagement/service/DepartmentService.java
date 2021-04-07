package com.ozeeesoftware.employeemanagement.service;

import com.ozeeesoftware.employeemanagement.model.Department;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;


public interface DepartmentService {
    ResponseEntity getAllDepartments();
    ResponseEntity getDepartmentById(long id);
    ResponseEntity createDepartment(Department department);
    ResponseEntity updateDepartment(Department department)throws InvocationTargetException, IllegalAccessException;
    ResponseEntity deleteDepartment(Department department);
    ResponseEntity deleteDepartmentById(long id);
}
