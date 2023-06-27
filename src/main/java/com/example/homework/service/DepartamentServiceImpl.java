package com.example.homework.service;

import com.example.homework.exception.EmployeeAlreadyAddedException;
import com.example.homework.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentServiceImpl implements DepartamentService {
    private final EmployeeService employeeService;

    public DepartamentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalary(int departament) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());

        return employeeList.stream()
                .filter(employee -> employee.getDepartament() == departament)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee minSalary(int departament) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());
        return employeeList.stream()
                .filter(e -> e.getDepartament() == departament)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElse(null);
    }

    @Override
    public List<Employee> returnAll() {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());
        return employeeList;
    }

    @Override
    public List<Employee> all(int departamentId) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());
        return employeeList.stream()
                .filter(e -> e.getDepartament() == departamentId)
                .collect(Collectors.toList());
    }
}
