package com.ozeeesoftware.employeemanagement.service;

import com.ozeeesoftware.employeemanagement.exception.NotFoundByIdException;
import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public ResponseEntity<Department> getDepartmentById(long id){
        Department department = departmentRepository.findById(id).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+id));
        return ResponseEntity.ok(department);
    }

    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    public ResponseEntity<Department> updateDepartment(Department department){
        Department existingDepartment=departmentRepository.findById(department.getId()).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+department.getId()));
        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setMaxSalary(department.getMaxSalary());
        existingDepartment.setMinSalary(department.getMinSalary());

        return ResponseEntity.ok(departmentRepository.save(existingDepartment));
    }

    public ResponseEntity<Map<String,Boolean>> deleteDepartment(Department department){
        Department existingDepartment=departmentRepository.findById(department.getId()).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+department.getId()));
        departmentRepository.delete(existingDepartment);
        Map<String,Boolean> response=new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String,Boolean>> deleteDepartmentById(long id){
        Department department=departmentRepository.findById(id).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+id));
        departmentRepository.delete(department);
        Map<String,Boolean> response=new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
