package com.ozeeesoftware.employeemanagement.employee;

import com.ozeeesoftware.employeemanagement.model.Department;
import com.ozeeesoftware.employeemanagement.model.Employee;
import com.ozeeesoftware.employeemanagement.model.ImageModel;
import com.ozeeesoftware.employeemanagement.repository.DepartmentRepository;
import com.ozeeesoftware.employeemanagement.repository.EmployeeRepository;
import com.ozeeesoftware.employeemanagement.repository.ImageModelRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ImageModelRepository imageModelRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ImageModelRepository imageModelRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.imageModelRepository = imageModelRepository;
    }



    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }

    @Test
    public void testGetAllEmployeesByDepartmentId(){
        Department department=new Department();
        department.setId(1L);
        departmentRepository.save(department);

        ImageModel imageModel =new ImageModel();
        imageModelRepository.save(imageModel);

        List<Employee> employeeList=new ArrayList<Employee>();
        employeeList.add(new Employee(1L,"test-firstName","test-lastName"
                ,department,(short)1,"test-address",1,"1","test-mail",imageModel));

        employeeList.add(new Employee(2L,"test-firstName2","test-lastName2"
                ,department,(short)1,"test-address",1,"1","test-mail2",imageModel));

        employeeRepository.saveAll(employeeList);

        List<Employee> existingEmployees=employeeRepository.findAllByDepartmentId(1L);

        assertThat(existingEmployees).isNotNull();

        assertEquals(employeeList,existingEmployees);

        assertEquals(2,existingEmployees.size());

    }


}
