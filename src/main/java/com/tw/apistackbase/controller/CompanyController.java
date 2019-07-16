package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/companys")
public class CompanyController {
    static List<Company> companies=new ArrayList<>();
    static List<Employee> employees = new ArrayList<>();
    static {
        Employee employee=new Employee(1,"dd",20,"man",30);
        employees.add(employee);
        Company company=new Company(1,"mycompany",34,employees);
        companies.add(company);
    }
    @GetMapping()
    public ResponseEntity getAllCompanys(@RequestParam(value = "page",required = false)String page,
                                         @RequestParam(value = "pageSize",required = false)String pageSize ) {
        List<Company> list = new ArrayList<>();
        if(page!=null&&pageSize!=null){
            int page1 = Integer.valueOf(page);
            int pageSize1 = Integer.valueOf(pageSize);
            for (int i = page1 * pageSize1 - pageSize1; i < page1 * pageSize1; i++) {
                list.add(companies.get(i));
            }
            return ResponseEntity.ok().body(list);
      }

        return ResponseEntity.ok().body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCompany(@PathVariable("id")String id) {
        Company company = companies.stream().filter(e -> e.getId() == Integer.valueOf(id)).collect(Collectors.toList()).get(0);
        return ResponseEntity.ok().body(company);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity getEmployess(@PathVariable("id")String id) {
        Company company = companies.stream().filter(e -> e.getId() == Integer.valueOf(id)).collect(Collectors.toList()).get(0);

        return ResponseEntity.ok().body(company.getEmployees());
    }
//    @GetMapping()
//    public ResponseEntity getCompanyByPage(@RequestParam("page")String page,@RequestParam("pageSize")String pageSize) {
//        int page1 = Integer.valueOf(page);
//        int pageSize1 = Integer.valueOf(pageSize);
//        List<Company> list = new ArrayList<>();
//        for (int i = page1 * pageSize1 - pageSize1; i < page1 * pageSize1; i++) {
//            list.add(companies.get(i));
//        }
//        return ResponseEntity.ok().body(list);
//    }

    @PostMapping()
    public ResponseEntity addCompany(@RequestBody Company company) {
        companies.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity addCompany(@PathVariable("id") String id,@RequestBody Company company) {
        Company company1 = companies.stream().filter(e -> e.getId() == Integer.valueOf(id)).collect(Collectors.toList()).get(0);
        companies.remove(company1);
        company1=company;
        companies.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(companies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        Company company1 = companies.stream().filter(e -> e.getId() == Integer.valueOf(id)).collect(Collectors.toList()).get(0);
        companies.remove(company1);
        return ResponseEntity.ok().build();
    }

}
