package com.example.homework.service;

import com.example.homework.model.Employee;

import java.util.List;

public interface DepartamentService {
    List<Employee> maxSalary(int departament);
    Employee minSalary(int departament);
    Employee returnAll(int departament);

}
