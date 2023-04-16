package com.example.homework.service;

import com.example.homework.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int salary, int departament);
    Employee remove(String firstName, String lastName, int salary, int departament);
    Employee find(String firstName, String lastName, int salary, int departament);

    Collection<Employee> findAll();
    
}
