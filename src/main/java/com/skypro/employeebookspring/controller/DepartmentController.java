package com.skypro.employeebookspring.controller;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/department")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartments() {
        return departmentService.getEmployeesGroupedByDepartments();
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getDepartmentEmployees(@PathVariable("id") int id) {
        return departmentService.getDepartmentEmployees(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSumOfSalariesByDepartment(@PathVariable("id") int id) {
        return departmentService.getSumOfSalariesByDepartment(id);
    }
    @GetMapping("/{id}/salary/min")
    public int getMinSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.getMinSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.getMaxSalaryByDepartment(id);
    }
}
