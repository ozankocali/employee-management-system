package com.ozeeesoftware.employeemanagement.service;

import com.ozeeesoftware.employeemanagement.model.Employee;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    ResponseEntity getAllEmployees();
    ResponseEntity getEmployeeById(Long id);
    ResponseEntity createEmployee(Employee employee);
    ResponseEntity updateEmployee(Employee employee)throws InvocationTargetException, IllegalAccessException;
    ResponseEntity deleteEmployee(Employee employee);
    ResponseEntity deleteEmployeeById(Long id);
    ResponseEntity findAllByDepartmentId(Long id);
}
