package com.example.homework;

import com.example.homework.exception.DepartmentNotFoundException;
import com.example.homework.exception.EmployeeNotFoundException;
import com.example.homework.model.Employee;
import com.example.homework.service.DepartamentServiceImpl;
import com.example.homework.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartamentServiceImpl departamentService;
    private List<Employee> employees;

    public static Stream<Arguments> maxSalaryTestParam() {
        return Stream.of(
                Arguments.of(1,15_000),
                Arguments.of(2,23_000)
        );
    }

    public static Stream<Arguments> minSalaryTestParam() {
        return Stream.of(
                Arguments.of(1, 10_000),
                Arguments.of(2, 20_000)
        );
    }

    public static Stream<Arguments> employeeFromDepartamentTestParam() {
        return Stream.of(
                Arguments.of(1,
                        List.of(
                                new Employee("Иван", "Иванов", 10_000, 1),
                                new Employee("Петр", "Петров", 15_000, 1)
                        )
                ),
                Arguments.of(2,
                        List.of(
                                new Employee("Петр", "Иванов", 20_000, 2),
                                new Employee("Анна", "Иванова", 23_000, 2)
                        )
                )
        );
    }

    public static Stream<Arguments> sumSalaryTestParam() {
        return Stream.of(
                Arguments.of(1, 25_000),
                Arguments.of(2, 43_000)
        );
    }

    @BeforeEach
    public void beforeEach() {
        employees = List.of(
                new Employee("Иван", "Иванов", 10_000, 1),
                new Employee("Петр", "Петров", 15_000, 1),
                new Employee("Петр", "Иванов", 20_000, 2),
                new Employee("Анна", "Иванова", 23_000, 2)
        );
        Mockito.when(employeeService.findAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("maxSalaryTestParam")
    public void maxSalaryTest(int departmentId, int expected) {
        Assertions.assertThat(departamentService.maxSalary(departmentId))
                .isEqualTo(expected);

    }

    @Test
    public void maxSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departamentService.maxSalary(3));

    }

    @ParameterizedTest
    @MethodSource("minSalaryTestParam")
    public void minSalaryTest(int departmentId, int expected) {
        Assertions.assertThat(departamentService.minSalary(departmentId))
                .isEqualTo(expected);

    }

    @Test
    public void minSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departamentService.minSalary(3));

    }



    @ParameterizedTest
    @MethodSource("sumSalaryTestParam")
    public void sumSalaryTest(int departmentId, int expected) {
        Assertions.assertThat(departamentService.sumSalary(departmentId))
                .isEqualTo(expected);

    }







    @ParameterizedTest
    @MethodSource("employeeFromDepartamentTestParam")
    public void employeeFromDepartamentTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departamentService.all(departmentId))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void employeeGroupByDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Иван", "Иванов", 10_000, 1),
                        new Employee("Петр", "Петров", 15_000, 1)
                ),
                2,
                List.of(
                        new Employee("Петр", "Иванов", 20_000, 2),
                        new Employee("Анна", "Иванова", 23_000, 2)

                )
        );
        Assertions.assertThat(departamentService.employeeGroupByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }
}
