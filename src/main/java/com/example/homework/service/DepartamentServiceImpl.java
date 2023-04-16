package com.example.homework.service;

import com.example.homework.exception.EmployeeAlreadyAddedException;
import com.example.homework.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentServiceImpl implements DepartamentService {
     EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Override
    public List<Employee> maxSalary(int departament) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());
        return employeeList;
    }

    @Override
    public Employee minSalary(int departament) {
        return null;
    }

    @Override
    public Employee returnAll(int departament) {
        return null;
    }
}
