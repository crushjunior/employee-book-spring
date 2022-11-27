package com.skypro.employeebookspring.service;


import com.skypro.employeebookspring.exception.EmployeeNotFoundException;
import com.skypro.employeebookspring.exception.InvalidEmployeeRequestException;
import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }


    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (!StringUtils.isAlpha(employeeRequest.getFirstName()) || !StringUtils.isAlpha(employeeRequest.getLastName())) {
            throw new InvalidEmployeeRequestException();
        }
        Employee employee = new Employee(
                StringUtils.capitalize(employeeRequest.getFirstName()),
                StringUtils.capitalize(employeeRequest.getLastName()),
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

    public Employee getEmployeeWithSalaryMin() {
        return employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee getEmployeeWithSalaryMax() {
        return employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    private Double getAverageSalary() {
        return employees.values().stream()
                .mapToDouble(Employee::getSalary).average().orElse(0D);
    }

    public List<Employee> getEmployeeWithSalaryMoreAverage() {
        var averageSalary = getAverageSalary();
        if (averageSalary == null) {
            return Collections.emptyList();
        }
        return employees.values().stream()
                .filter(e -> e.getSalary() >= averageSalary)
                .collect(Collectors.toList());
    }

    public Employee removeEmployee(int id) {
        return employees.remove(id);
    }

}
