package com.ozeeesoftware.employeemanagement.employee;


import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.model.Employee;
import com.ozeeesoftware.employeemanagement.model.ImageModel;
import com.ozeeesoftware.employeemanagement.repository.EmployeeRepository;
import com.ozeeesoftware.employeemanagement.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
public class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    public void testGetAllEmployees(){
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        List<Employee> employeeList=new ArrayList<Employee>();
        employeeList.add(new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel));

        employeeList.add(new Employee(2L,"test-firstName2","test-lastName2"
                ,department,(short)1,"test-address",1,"1","test-mail2",imageModel));

        when(employeeRepository.findAll()).thenReturn(employeeList);

        assertEquals(2,employeeService.getAllEmployees().getBody().size());
    }

    @Test
    public void testGetEmployeeById(){
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertEquals(employee,employeeService.getEmployeeById(1L).getBody());
    }

    @Test
    public void testGetAllEmployeesByDepartmentId(){
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        List<Employee> employeeList=new ArrayList<Employee>();
        employeeList.add(new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel));

        employeeList.add(new Employee(2L,"test-firstName2","test-lastName2"
                ,department,(short)1,"test-address",1,"1","test-mail2",imageModel));

        when(employeeRepository.findAllByDepartmentId(1L)).thenReturn(employeeList);

        assertEquals(2,employeeService.findAllByDepartmentId(1L).getBody().size());
    }


    @Test
    public void testCreateEmployee(){
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        when(employeeRepository.save(employee)).thenReturn(employee);

        assertEquals(employee,employeeService.createEmployee(employee).getBody());


    }


    @Test
    public void testDeleteEmployee(){
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(employee);

        verify(employeeRepository,times(1)).delete(employee);

    }

    @Test
    public void testDeleteEmployeeById(){
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(employee.getId());

        verify(employeeRepository,times(1)).delete(employee);

    }



}
