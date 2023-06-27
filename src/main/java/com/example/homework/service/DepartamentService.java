package com.example.homework.service;

import com.example.homework.model.Employee;

import java.util.List;

public interface DepartamentService {
    Employee maxSalary(int departament);
    Employee minSalary(int departament);
    List<Employee> returnAll();

    List<Employee> all(int departamentId);
}
