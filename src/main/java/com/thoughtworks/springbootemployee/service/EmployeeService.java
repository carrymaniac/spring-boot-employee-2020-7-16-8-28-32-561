package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getEmployees(){
        Employee employee1 = new Employee("1", 18, "female", "eva", 10000);
        Employee employee2 = new Employee("2", 24, "male", "java", 15000);
        Employee employee3 = new Employee("3", 24, "male", "gradle", 12000);
        Employee employee4 = new Employee("4", 20, "male", "ace", 8000);
        Employee employee5 = new Employee("5", 20, "female", "iris", 18000);
        return Arrays.asList(employee1,employee2,employee3,employee4,employee5);
    }
}
