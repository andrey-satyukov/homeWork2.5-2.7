package com.example.homework.service;

import com.example.homework.exception.DepartmentNotFoundException;
import com.example.homework.exception.EmployeeAlreadyAddedException;
import com.example.homework.exception.EmployeeNotFoundException;
import com.example.homework.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartamentServiceImpl implements DepartamentService {
    private final EmployeeService employeeService;

    public DepartamentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public int sumSalary(int departament) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());

        return employeeList.stream()
                .filter(employee -> employee.getDepartament() == departament)
                .mapToInt(Employee::getSalary)
                .sum();

    }
    @Override
    public int maxSalary(int departament) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());

        return employeeList.stream()
                .filter(employee -> employee.getDepartament() == departament)
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
    }
    @Override
    public int minSalary(int departament) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());
        return employeeList.stream()
                .filter(employee -> employee.getDepartament() == departament)
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
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

    @Override
    public Map<Integer, List<Employee>> employeeGroupByDepartment() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));
    }
}
