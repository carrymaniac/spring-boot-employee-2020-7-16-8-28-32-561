package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/{id}")
    Company getCompanyById(@PathVariable("id") String id) {
        return companyService.getCompanys().stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/{id}/employees")
    List<Employee> getEmployeesByCompanyId(@PathVariable("id") String id) {
        return companyService.getCompanys().stream()
                .filter(company -> company.getId().equals(id))
                .map(Company::getEmployees)
                .findFirst()
                .orElse(null);
    }

    @GetMapping
    List<Company> getCompaniesByPageAndSize(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "0") int size) {
        if (page == 0 && size == 0) {
            return this.companyService.getCompanys();
        }
        return this.companyService.getCompanys().subList((page - 1) * size, page * size);
    }

    @PostMapping
    String addCompany(@RequestBody Company company) {
        this.companyService.getCompanys().add(company);
        return "success";
    }

    @PutMapping
    String updateCompany(@RequestBody Company newCompany) {
        Company oldCompany = this.companyService.getCompanys().stream()
                .filter(company -> company.getId().equals(newCompany.getId()))
                .findFirst().orElse(null);
        if (oldCompany == null) {
            return "fail";
        }
        this.companyService.getCompanys().remove(oldCompany);
        this.companyService.getCompanys().add(newCompany);
        return "success";
    }

    @DeleteMapping("/{id}")
    String deleteCompany(@PathVariable("id") String id) {
        Company foundCompany = this.companyService.getCompanys().stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(foundCompany!=null){
            foundCompany.setEmployees(null);
            return "success";
        }else {
            return "fail";
        }
    }
}
