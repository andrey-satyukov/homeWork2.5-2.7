package com.example.homework.model;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final int salary;
    private final int departament;

    public Employee(String firstName, String lastName, int salary, int departament) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.departament = departament;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartament() {
        return departament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && departament == employee.departament && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary, departament);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", departament=" + departament +
                '}';
    }
}
