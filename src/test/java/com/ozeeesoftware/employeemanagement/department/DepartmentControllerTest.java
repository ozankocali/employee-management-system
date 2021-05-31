package com.ozeeesoftware.employeemanagement.department;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozeeesoftware.employeemanagement.controller.DepartmentController;
import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.service.DepartmentServiceImpl;
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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentServiceImpl departmentService;

    String url="/api/v1/departments";

    @Test
    public void testGetAllDepartments()throws Exception{
        List<Department> departmentList=new ArrayList<Department>();

        departmentList.add(new Department(1L,"test-name",1,1,"test-description","1"));
        departmentList.add(new Department(2L,"test-name2",1,1,"test-description2","1"));

        when(departmentService.getAllDepartments()).thenReturn(new ResponseEntity<List<Department>>(departmentList, HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(departmentList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testGetDepartmentById()throws Exception{
        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        when(departmentService.getDepartmentById(1L)).thenReturn(new ResponseEntity<Department>(department,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url+"/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(department);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testCreateDepartment()throws Exception{

        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        when(departmentService.createDepartment(department)).thenReturn(new ResponseEntity<Department>(department,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(post(url+"/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse =mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(department);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }

    @Test
    public void testUpdateDepartment()throws Exception{
        Department existingDepartment=new Department(1L,"test-name",1,1,"test-description","1");

        Department updatedDepartment=new Department(1L,"test-name2",1,1,"test-description2","1");

        when(departmentService.updateDepartment(updatedDepartment)).thenReturn(new ResponseEntity<Department>(updatedDepartment,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(put(url+"/update").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedDepartment)))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(updatedDepartment);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

        assertThat(actualJsonResponse).isNotEqualToIgnoringWhitespace(objectMapper.writeValueAsString(existingDepartment));

    }

    @Test
    public void testDeleteDepartment()throws Exception{
        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        when(departmentService.deleteDepartment(department)).thenReturn(new ResponseEntity<Map<String, Boolean>>(response,HttpStatus.OK));

        mockMvc.perform(delete(url+"/delete").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(department))).andExpect(status().isOk());

    }

    @Test
    public void testDeleteDepartmenById()throws Exception{
        Department department=new Department(1L,"test-name",1,1,"test-description","1");

        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        when(departmentService.deleteDepartmentById(department.getId())).thenReturn(new ResponseEntity<Map<String, Boolean>>(response,HttpStatus.OK));

        mockMvc.perform(delete(url+"/delete/1")
                ).andExpect(status().isOk());

    }

}
