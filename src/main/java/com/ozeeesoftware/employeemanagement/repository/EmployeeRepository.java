package com.ozeeesoftware.employeemanagement.repository;

import com.ozeeesoftware.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByDepartmentId(Long id);
}
