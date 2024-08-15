package com.employee.repository;

import com.employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Employee> findByEmail(String email) {
        List<Employee> employees = entityManager.createNamedQuery("Employee.findByEmail", Employee.class)
                .setParameter("email", email)
                .getResultList();
        return employees;
    }

}
