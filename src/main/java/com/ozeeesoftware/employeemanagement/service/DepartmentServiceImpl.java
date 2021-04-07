package com.ozeeesoftware.employeemanagement.service;

import com.ozeeesoftware.employeemanagement.exception.NotFoundByIdException;
import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.repository.DepartmentRepository;
import com.ozeeesoftware.employeemanagement.util.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @Override
    public ResponseEntity<Department> getDepartmentById(long id){
        Department department = departmentRepository.findById(id).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+id));
        return ResponseEntity.ok(department);
    }

    @Override
    public ResponseEntity<Department> createDepartment(Department department){
        return ResponseEntity.ok(departmentRepository.save(department));
    }

    @Override
    public ResponseEntity<Department> updateDepartment(Department department)throws InvocationTargetException, IllegalAccessException {
        Department existingDepartment=departmentRepository.findById(department.getId()).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+department.getId()));
        if(existingDepartment==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        BeanUtilsBean exists=new NullAwareBeanUtilsBean();
        exists.copyProperties(existingDepartment,department);
        return ResponseEntity.ok(departmentRepository.save(existingDepartment));
    }

    @Override
    public ResponseEntity<Map<String,Boolean>> deleteDepartment(Department department){
        Department existingDepartment=departmentRepository.findById(department.getId()).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+department.getId()));
        departmentRepository.delete(existingDepartment);
        Map<String,Boolean> response=new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String,Boolean>> deleteDepartmentById(long id){
        Department department=departmentRepository.findById(id).orElseThrow(()-> new NotFoundByIdException("Department not exist with id: "+id));
        departmentRepository.delete(department);
        Map<String,Boolean> response=new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
