package com.ozeeesoftware.employeemanagement.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozeeesoftware.employeemanagement.controller.EmployeeController;
import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.model.Employee;
import com.ozeeesoftware.employeemanagement.model.ImageModel;
import com.ozeeesoftware.employeemanagement.repository.EmployeeRepository;
import com.ozeeesoftware.employeemanagement.service.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    String url="/api/v1/employees";

    @Test
    public void testGetAllEmployees()throws Exception{
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        List<Employee> employeeList=new ArrayList<Employee>();
        employeeList.add(new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel));

        employeeList.add(new Employee(2L,"test-firstName2","test-lastName2"
                ,department,(short)1,"test-address",1,"1","test-mail2",imageModel));

        when(employeeService.getAllEmployees()).thenReturn(new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(employeeList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }


    @Test
    public void testGetStudentById()throws Exception{

        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        when(employeeService.getEmployeeById(1L)).thenReturn(new ResponseEntity<Employee>(employee, HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(employee);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }

    @Test
    public void testGetAllEmployeesByDepartmentId()throws Exception{
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        List<Employee> employeeList=new ArrayList<Employee>();
        employeeList.add(new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel));

        employeeList.add(new Employee(2L,"test-firstName2","test-lastName2"
                ,department,(short)1,"test-address",1,"1","test-mail2",imageModel));

        when(employeeService.findAllByDepartmentId(1L)).thenReturn(new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url+"/byDepartment/1")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(employeeList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testCreateEmployee()throws Exception{
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        when(employeeService.createEmployee(employee)).thenReturn(new ResponseEntity<Employee>(employee,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(post(url+"/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(employee);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testUpdateEmployee()throws Exception{
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee existingEmployee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        Employee updatedEmployee=new Employee(1L,"test-firstName2","test-lastName2"
                ,department,(short)1,"test-address",1,"1","test-mail2",imageModel);

        when(employeeService.updateEmployee(updatedEmployee)).thenReturn(new ResponseEntity<Employee>(updatedEmployee,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(put(url+"/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(updatedEmployee);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

        assertThat(actualJsonResponse).isNotEqualToIgnoringWhitespace(objectMapper.writeValueAsString(existingEmployee));

    }

    @Test
    public void testDeleteEmployee()throws Exception{
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        when(employeeService.deleteEmployee(employee)).thenReturn(new ResponseEntity<Map<String,Boolean>>(response,HttpStatus.OK));

        mockMvc.perform(delete(url+"/delete").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployeeById()throws Exception{
        Department department=new Department();

        ImageModel imageModel =new ImageModel();

        Employee employee=new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel);

        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        when(employeeService.deleteEmployeeById(employee.getId())).thenReturn(new ResponseEntity<Map<String,Boolean>>(response,HttpStatus.OK));

        mockMvc.perform(delete(url+"/delete/1")).andExpect(status().isOk());

    }




}
