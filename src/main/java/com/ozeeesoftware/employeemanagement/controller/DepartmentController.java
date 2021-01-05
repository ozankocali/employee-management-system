package com.ozeeesoftware.employeemanagement.controller;

import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/departments/save")
    public Department createDepartment(@RequestBody Department department){
        return departmentService.createDepartment(department);
    }

    @PutMapping("/departments/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/departments/delete")
    public ResponseEntity<Map<String,Boolean>> deleteDepartment(@RequestBody Department department){
        return departmentService.deleteDepartment(department);
    }

    @DeleteMapping("/departments/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteDepartmentById(@PathVariable long id){
        return departmentService.deleteDepartmentById(id);
    }
}
