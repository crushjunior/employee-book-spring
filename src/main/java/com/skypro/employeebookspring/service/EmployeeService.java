package com.skypro.employeebookspring.service;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public void addEmployee() {

    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee's name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }


    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getSalaryMin() {
        var minSalary = employees.values().stream().mapToInt(Employee::getSalary).min().getAsInt();
        return employees.values().stream().filter(e -> e.getSalary() == minSalary).findAny().get();
    }

    public Employee getSalaryMax() {
        var maxSalary = employees.values().stream().mapToInt(Employee::getSalary).max().getAsInt();
        return employees.values().stream().filter(e -> e.getSalary() == maxSalary).findAny().get();
    }

    public List<Employee> getSalaryMoreMedium() {
        var mediumSalary = employees.values().stream().mapToInt(Employee::getSalary).average().getAsDouble();
        return employees.values().stream().filter(e -> e.getSalary() >= mediumSalary).collect(Collectors.toList());
    }

}
