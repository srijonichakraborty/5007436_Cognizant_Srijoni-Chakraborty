package com.employee.repository;

import com.employee.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findByEmail(String email);
}
