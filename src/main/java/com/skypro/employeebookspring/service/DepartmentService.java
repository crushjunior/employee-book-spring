package com.skypro.employeebookspring.service;

import com.skypro.employeebookspring.exception.EmployeeNotFoundException;
import com.skypro.employeebookspring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Stream<Employee> getEmployeesByDepartmentStream(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == department);
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartments() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> getDepartmentEmployees(int department) {
        return getEmployeesByDepartmentStream(department)
                .collect(Collectors.toList());
    }

    public int getSumOfSalariesByDepartment(int department) {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMaxSalaryByDepartment(int department) {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary).max()
                .orElseGet(() -> 0);
    }

    public int getMinSalaryByDepartment(int department) {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary).min()
                .orElseGet(() -> 0);
    }
}
