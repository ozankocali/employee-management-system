package com.ozeeesoftware.employeemanagement.service;

import com.ozeeesoftware.employeemanagement.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    ResponseEntity getAllEmployees();
    ResponseEntity getEmployeeById(long id);
    ResponseEntity createEmployee(Employee employee);
    ResponseEntity updateEmployee(Employee employee);
    ResponseEntity deleteEmployee(Employee employee);
    ResponseEntity deleteEmployeeById(long id);
    ResponseEntity findAllByDepartmentId(long id);
}
