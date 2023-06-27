package com.example.homework.controller;

import com.example.homework.model.Employee;
import com.example.homework.service.DepartamentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartamentController {
    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @GetMapping(value = "/{id}/employees ")
    public List<Employee> all(@PathVariable int id) {
        return departamentService.all(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public int sumSalary(@PathVariable int id) {
        return departamentService.sumSalary(id);
    }

    @GetMapping(path = "/{id}/salary/max")
    public int maxSalary(@PathVariable int id) {
        return departamentService.maxSalary(id);
    }
    @GetMapping(path = "/{id}/salary/min")
    public int minSalary(@PathVariable int id) {
        return departamentService.minSalary(id);
    }




    @GetMapping(path = "all")
    public List<Employee> allDepartment() {
        return departamentService.returnAll();
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> employeeGroupByDepartment() {
        return departamentService.employeeGroupByDepartment();
    }
}
