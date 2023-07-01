package com.example.collectionssheetsandsets.service;

import com.example.collectionssheetsandsets.exception.EmployeeAlreadyAddedException;
import com.example.collectionssheetsandsets.exception.EmployeeNotFoundException;
import com.example.collectionssheetsandsets.exception.EmployeeStorageIsFullException;
import com.example.collectionssheetsandsets.start.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int MAXSIZE = 5;

    public Employee add(String fistName,String lastName) {
        if (employees.size() >= MAXSIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeAdd = new Employee(fistName, lastName);
        if (employees.contains(employeeAdd)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employeeAdd);
        return employeeAdd;
    } // добавим сотрудника

    public Employee remove(String fistName,String lastName) {
        Employee employeeRemove = new Employee(fistName, lastName);
        if (!employees.contains(employeeRemove)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employeeRemove);
        return employeeRemove;
    } // Удалим сотрудника

    public Employee find(String firstName, String lastName) {
        Employee employeeFind = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (firstName.equalsIgnoreCase(employee.getFirstName()) && lastName.equalsIgnoreCase
                    (employee.getLastName())) {
                //если имя и фамилия совпадают то вернем текущий объект
                return employee;
            }
        }
        throw new EmployeeNotFoundException();

    }
    public List<Employee> getall(){
        return Collections.unmodifiableList(employees);

    }
}