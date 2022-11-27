package com.skypro.employeebookspring;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.service.DepartmentService;
import com.skypro.employeebookspring.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    private final List<Employee> employees = List.of(
            new Employee("Test", "Test", 1, 10000),
            new Employee("Testik", "Testik", 2, 11000),
            new Employee("Testov", "Testov", 1, 12000),
            new Employee("Tes", "Tes", 2, 13000),
            new Employee("Tesak", "Tesak", 1, 14000)
    );

    @BeforeEach
    public void setUp() {
        when(employeeService.getAllEmployees()).thenReturn(employees);
    }

    @Test
    public void shouldReturnEmployeesGroupedByDepartments() {
        Map<Integer, List<Employee>> actual = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        assertEquals(actual, departmentService.getEmployeesGroupedByDepartments());
    }

    @Test
    public void shouldReturnDepartmentEmployees() {
        int department = 1;
        List<Employee> actual = employees.stream().filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        assertEquals(actual, departmentService.getDepartmentEmployees(department));
    }

    @Test
    public void shouldReturnSumOfSalariesByDepartment() {
        int department = 1;
        int actual = 36000; //employees.stream().filter(e -> e.getDepartment() == department)
                //.mapToInt(Employee::getSalary).sum();
        assertEquals(actual, departmentService.getSumOfSalariesByDepartment(department));
    }

    @Test
    public void shouldReturnMaxSalaryByDepartment() {
        int department = 1;
        int actual = 14000; //employees.stream().filter(e -> e.getDepartment() == department)
                        //.mapToInt(Employee::getSalary).max().orElse(0);
        assertEquals(actual, departmentService.getMaxSalaryByDepartment(department));
    }

    @Test
    public void shouldReturnMinSalaryByDepartment() {
        int department = 1;
        int actual = 10000; //employees.stream().filter(e-> e.getDepartment() == department)
                //.mapToInt(Employee::getSalary).min().orElse(0);
        assertEquals(actual, departmentService.getMinSalaryByDepartment(department));
    }
}
