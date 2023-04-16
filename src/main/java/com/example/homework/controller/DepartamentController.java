package com.example.homework.controller;

import com.example.homework.service.DepartamentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/departament")
public class DepartamentController {
    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }
    ///departments/max-salary?departmentId=5
    @GetMapping(path = "/max-salary")
    public Collection maxSalary(@RequestParam("departmentId") int departmentId) {
        return departamentService.maxSalary(departmentId);
    }
}
