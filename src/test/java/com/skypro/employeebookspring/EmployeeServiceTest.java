package com.skypro.employeebookspring;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.record.EmployeeRequest;
import com.skypro.employeebookspring.service.EmployeeService;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("Test", "Test", 1, 10000),
                new EmployeeRequest("Testik", "Testik", 2, 11000),
                new EmployeeRequest("Testov", "Testov", 1, 12000),
                new EmployeeRequest("Tes", "Tes", 2, 13000),
                new EmployeeRequest("Tesak", "Tesak", 1, 14000)
        ).forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Ivan", "Chmih", 3, 15000);
        Employee testEmployee = employeeService.addEmployee(employeeRequest);
        Assertions.assertThat(employeeService.getAllEmployees()).contains(testEmployee);
    }

    @Test
    public void getAllEmployees() {
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(5);
        Assertions.assertThat(employees).first().extracting(Employee::getFirstName).isEqualTo("Test");
    }

    @Test
    public void removeEmployee() {
        employeeService.removeEmployee(1);
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(4);
    }

    @Test
    public void getSalarySum() {
        int sum = employeeService.getSalarySum();
        Assertions.assertThat(sum).isEqualTo(60000);
    }

    @Test
    public void getEmployeeWithSalaryMin() {
        Employee employeeMin = employeeService.getEmployeeWithSalaryMin();
        Assertions.assertThat(employeeMin).isNotNull().extracting(Employee::getFirstName).isEqualTo("Test");
    }

    @Test
    public void getEmployeeWithSalaryMax() {
        Employee employeeMax = employeeService.getEmployeeWithSalaryMax();
        Assertions.assertThat(employeeMax).isNotNull().extracting(Employee::getFirstName).isEqualTo("Tesak");
    }

    @Test
    public void getEmployeeWithSalaryMoreAverage() {
        Collection<Employee> employees = employeeService.getAllEmployees().stream()
                        .filter(e -> e.getSalary() >= (double) (10000 + 11000 + 12000 + 13000 + 14000)/5)
                .collect(Collectors.toList());
        Assertions.assertThat(employeeService.getEmployeeWithSalaryMoreAverage()).isEqualTo(employees);
    }
}
