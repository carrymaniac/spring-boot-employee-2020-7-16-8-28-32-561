package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "pageSize", defaultValue = "0") int size,
                                       @RequestParam(value = "gender",required = false)String gender) {
        if (page == 0 && size == 0 && gender==null) {
            return this.employeeService.getEmployees();
        }else if(gender!=null){
            return this.employeeService.getEmployees()
                    .stream().filter(employee -> employee.getGender().equals(gender))
                    .collect(Collectors.toList());
        }
        return this.employeeService.getEmployees().subList((page - 1) * size, page * size);
    }

    @GetMapping("/{id}")
    public Employee getEmploy(@PathVariable("id") String id) {
        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public String updateEmploy(@PathVariable("id") String id, @RequestBody Employee newEmployee) {
        newEmployee.setId(id);
        Employee oldEmployee = this.employeeService.getEmployees().stream()
                .filter(employee -> employee.getId().equals(newEmployee.getId()))
                .findFirst().orElse(null);
        if (oldEmployee == null) {
            return "fail";
        }
        this.employeeService.getEmployees().remove(oldEmployee);
        this.employeeService.getEmployees().add(newEmployee);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        Employee foundEmployee = employeeService.getEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (foundEmployee != null) {
            employeeService.getEmployees().remove(foundEmployee);
            return "success";
        } else {
            return "fail";
        }
    }


}
