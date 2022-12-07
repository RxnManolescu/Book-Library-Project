package com.employee.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
