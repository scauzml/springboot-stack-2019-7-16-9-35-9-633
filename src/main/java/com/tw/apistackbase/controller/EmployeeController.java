package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@RestController()
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger log = Logger.getLogger(this.getClass().getName());
   private List<Employee> employees = new ArrayList<Employee>();

     public EmployeeController(){
         employees.add(new Employee(1,"dd1",20,"man",30));
         employees.add(new Employee(2,"dd1",20,"man",30));
         employees.add(new Employee(3,"dd1",20,"man",30));
         employees.add(new Employee(4,"dd1",20,"man",30));
         employees.add(new Employee(5,"dd1",20,"flmale",30));
     }
    @GetMapping()
    public ResponseEntity getEmployee(@RequestParam(value = "page",required = false)String page,
                                      @RequestParam(value = "pageSize",required = false)String pageSize,
                                      @RequestParam(value = "gender",required = false)String gender) {
        List<Employee> list = new ArrayList<>();
        System.out.println(gender);
        if(page!=null&&pageSize!=null){
            int page1 = Integer.valueOf(page);
            int pageSize1 = Integer.valueOf(pageSize);
            int length=page1 * pageSize1;
            if (length > employees.size()) {
                 length=employees.size();
            }
            for (int i = page1 * pageSize1 - pageSize1; i < 5; i++) {
                list.add(employees.get(i));
            }
            return ResponseEntity.ok().body(list);
        } else if (gender != null) {
            List<Employee> employeeList=employees.stream().filter(e-> e.getGender().equals(gender)).collect(Collectors.toList());
            return ResponseEntity.ok().body(employeeList);
        }

        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id")String id) {
        Employee employee = employees.stream().filter(e -> e.getId() == Integer.valueOf(id)).collect(Collectors.toList()).get(0);
        return ResponseEntity.ok().body(employee);
    }
//    @GetMapping()
//    public ResponseEntity getEmployeeByPage(@RequestParam("page")String page,@RequestParam("pageSize")String pageSize) {
//        int page1 = Integer.valueOf(page);
//        int pageSize1 = Integer.valueOf(pageSize);
//        List<Employee> list = new ArrayList<>();
//        for (int i = page1 * pageSize1 - pageSize1; i < page1 * pageSize1; i++) {
//            list.add(employees.get(i));
//        }
//        return ResponseEntity.ok().body(list);
//    }
//    @GetMapping()
//    public ResponseEntity getEmployeeByGender(@RequestParam("gender")String gender) {
//        List<Employee> employeeList=employees.stream().filter(e->e.getGender()==gender).collect(Collectors.toList());
//        return ResponseEntity.ok().body(employeeList);
//    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Employee employee) {

        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userid}")
    public ResponseEntity change(@RequestBody Employee employee,@PathVariable("userid")String id) {
        Employee employee1 = employees.stream().filter(e -> e.getId() == Integer.valueOf(id)).collect(Collectors.toList()).get(0);
        employees.remove(employee1);
        employee1=employee;
        employees.add(employee1);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee1);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity delete(@PathVariable("userid") String id) {
        Employee employee1 = employees.stream().filter(e -> e.getId() == Integer.valueOf(id)).collect(Collectors.toList()).get(0);
        employees.remove(employee1);
        return ResponseEntity.ok().build();
    }
}
