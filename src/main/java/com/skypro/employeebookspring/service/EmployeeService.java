package com.skypro.employeebookspring.service;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;

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
        int minSalary = Integer.MAX_VALUE;
        Employee employeeWithMinSalary = null;
        for (Map.Entry<Integer, Employee> employee : employees.entrySet()){
            if (employee != null) {
                if (minSalary > employee.getValue().getSalary()) {
                    minSalary = employee.getValue().getSalary();
                    employeeWithMinSalary = employee.getValue();
                }
            }
        }
        if (employeeWithMinSalary != null) {
            return employeeWithMinSalary;
        } else {
            return null;
        }
    }

    public Employee getSalaryMax() {
        int maxSalary = Integer.MIN_VALUE;
        Employee employeeWithMaxSalary = null;
        for (Map.Entry<Integer, Employee> employee : employees.entrySet()){
            if (employee != null) {
                if (maxSalary < employee.getValue().getSalary()) {
                    maxSalary = employee.getValue().getSalary();
                    employeeWithMaxSalary = employee.getValue();
                }
            }
        }
        if (employeeWithMaxSalary != null) {
            return employeeWithMaxSalary;
        } else {
            return null;
        }
    }

    public List<Employee> getSalaryMoreMedium() {
        int medium = 0;
        for (Map.Entry<Integer, Employee> employee : employees.entrySet()) {
            if (employee != null) {
                medium += employee.getValue().getSalary();
            }
        }
        medium /= employees.size();
        List<Employee> employeesWithMoreMediumSalary = new ArrayList<>();
        for (Map.Entry<Integer, Employee> employee : employees.entrySet()) {
            if (employee.getValue().getSalary() >= medium) {
                employeesWithMoreMediumSalary.add(employee.getValue());
            }
        }
        return employeesWithMoreMediumSalary;
    }
}
