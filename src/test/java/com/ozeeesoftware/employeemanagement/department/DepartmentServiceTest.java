package com.ozeeesoftware.employeemanagement.department;


import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.repository.DepartmentRepository;
import com.ozeeesoftware.employeemanagement.service.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DepartmentServiceTest {

    @MockBean
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void testGetAllDepartments(){
        List<Department> departmentList=new ArrayList<Department>();

        departmentList.add(new Department(1L,"test-name",1,1,"test-description","1"));
        departmentList.add(new Department(2L,"test-name2",1,1,"test-description2","1"));

        when(departmentRepository.findAll()).thenReturn(departmentList);

        assertEquals(2,departmentService.getAllDepartments().getBody().size());

    }

    @Test
    public void testGetDepartmentById(){
        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        assertEquals(department,departmentService.getDepartmentById(1L).getBody());

    }

    @Test
    public void testCreateDepartment(){
        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        when(departmentRepository.save(department)).thenReturn(department);

        assertEquals(department,departmentService.createDepartment(department).getBody());
    }

    @Test
    public void testDeleteDepartment(){
        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        departmentService.deleteDepartment(department);

        verify(departmentRepository,times(1)).delete(department);

    }

    @Test
    public void testDeleteDepartmentById(){
        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        departmentService.deleteDepartmentById(department.getId());

        verify(departmentRepository,times(1)).delete(department);

    }


}
