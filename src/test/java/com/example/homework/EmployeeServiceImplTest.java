package com.example.homework;

import com.example.homework.exception.EmployeeAlreadyAddedException;
import com.example.homework.exception.EmployeeNotFoundException;
import com.example.homework.exception.IncorrectNameException;
import com.example.homework.exception.IncorrectSurnameException;
import com.example.homework.model.Employee;
import com.example.homework.service.EmployeeService;
import com.example.homework.service.EmployeeServiceImpl;
import com.example.homework.service.ValidatorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeeServiceImplTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl(new ValidatorService());

    public static Stream<Arguments> addWithIncorrectNameTestParams() {
        return Stream.of(
                Arguments.of("Ivan1"),
                Arguments.of("Ivan-")
        );
    }

    public static Stream<Arguments> addWithIncorrectSurnameTestParams() {
        return Stream.of(
                Arguments.of("Ivanov1"),
                Arguments.of("Ivanov2")
        );
    }


    @BeforeEach
    public void beforeEach() {
        employeeService.add("Ivan", "Ivanov", 1, 10_000);
        employeeService.add("Petr", "Petrov", 2, 20_000);
        employeeService.add("Andrey", "Andreev", 3, 25_000);
    }

    @AfterEach
    public void afterEach() {
        employeeService.findAll().forEach(employee -> employeeService.remove(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartament()));
    }

    @Test
    public void addTest() {
        int beforeCount = employeeService.findAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 1, 10_000);
        Assertions.assertThat(employeeService.add("Ivan", "Ivanov", 1, 10_000))
                .isEqualTo(expected)
                .isIn(employeeService.findAll());
        Assertions.assertThat(employeeService.findAll()).hasSize(beforeCount + 1);
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectNameTestParams")
    public void addWithIncorerctNameTest(String incorrectName) {
        Assertions.assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.add(incorrectName, "Ivanov", 1, 10_000));


    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectSurnameTestParams")
    public void addWithIncorrectSurnameTest(String incorrectSurname) {
        Assertions.assertThatExceptionOfType(IncorrectSurnameException.class)
                .isThrownBy(() -> employeeService.add("Ivan", incorrectSurname, 1, 10_000));

    }

    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add("Ivan", "Ivanov", 1, 10_000));
    }
    @Test
    public void removeTest() {
        int beforeCount = employeeService.findAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 1, 10_000);
        Assertions.assertThat(employeeService.remove("Ivan", "Ivanov", 1, 10_000))
                .isEqualTo(expected)
                .isNotIn(employeeService.findAll());
        Assertions.assertThat(employeeService.findAll()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Ivan", "Ivanov", 1, 10_000));
    }
    @Test
    public void removeWhenNotFoundTest() {

        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Vasya" +
                        "", "Ivanov", 1, 10_000));
    }

}
