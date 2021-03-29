package com.ozeeesoftware.employeemanagement.controller;

import com.ozeeesoftware.employeemanagement.model.Employee;
import com.ozeeesoftware.employeemanagement.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees/save")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return  employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/delete")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@RequestBody Employee employee){
        return employeeService.deleteEmployee(employee);
    }

    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployeeById(@PathVariable long id){
        return employeeService.deleteEmployeeById(id);
    }

}
