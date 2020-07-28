package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    EmployeeService employeeService;

    public List<Company> getCompanys(){
        List<Company> companies = new ArrayList<>();
        List<Employee> employees = employeeService.getEmployees();
        companies.add(new Company("1", "OOCL", Arrays.asList(employees.get(0),employees.get(1))));
        companies.add(new Company("2", "ThoughtWorks", Collections.singletonList(employees.get(2))));
        return companies;
    }
}
