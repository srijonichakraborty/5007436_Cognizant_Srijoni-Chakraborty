package com.employee.repository;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

    // Derived query method to find employees by name
    List<Employee> findByName(String name);

    // Derived query method to find employees by department
    List<Employee> findByDepartmentId(Long departmentId);

    // Custom query to find employees by department name using @Query annotation
    @Query("SELECT new com.employee.dto.EmployeeDTO(e.id, e.name, e.email, d.name) " +
           "FROM Employee e JOIN e.department d WHERE d.name = :departmentName")
    List<EmployeeDTO> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    // Pagination and sorting for all employees
    Page<Employee> findAll(Pageable pageable);
}
