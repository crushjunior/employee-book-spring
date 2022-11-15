package com.skypro.employeebookspring.record;

import static org.apache.commons.lang3.StringUtils.*;

public class EmployeeRequest {
    private static final String VALID_SYMBOLS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMйцукенгшщзхъэждлорпавфыячсмитьбюёЁЙЦУКЕНГШЩЗХЪЭЖДЛОРПАВЫФЯЧСМИТЬБЮ";
    private  String firstName;
    private  String lastName;
    private  int department;
    private  int salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws RuntimeException {
        if (containsOnly(firstName, VALID_SYMBOLS)) {
            this.firstName = capitalize(firstName);
        } else {
            throw new RuntimeException("Wrong first name!");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws RuntimeException {
        if (containsOnly(lastName, VALID_SYMBOLS)) {
            this.lastName = capitalize(lastName);
        } else {
            throw new RuntimeException("Wrong last name!");
        }
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
