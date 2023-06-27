package com.example.homework.service;

import com.example.homework.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartamentService {
    int sumSalary(int departament);
    int maxSalary(int departament);
    int minSalary(int departament);
    List<Employee> returnAll();

    List<Employee> all(int departamentId);
    Map<Integer, List<Employee>> employeeGroupByDepartment();
}
