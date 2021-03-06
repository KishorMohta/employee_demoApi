package com.dummy.api.springbootconfig.controller;

import com.dummy.api.springbootconfig.Model.Emp_Pojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class Emp_Services {

    @Autowired
    RestTemplate restTemplate;

    Emp_Repository repo=new Emp_Repository();

    @GetMapping("/getEmps")
            public List<Emp_Pojo> getAllEmp(){
            System.out.println("In Get All employee");
        return repo.getAllEmp();
        }

    @RequestMapping(value = "/getEmp/{id}",method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    public Emp_Pojo getEmp(@PathVariable("id") int id){
        System.out.println("Get an employee of id"+id);
            return repo.getEmp(id);
        }

    @PostMapping("/postEmp")
    public ResponseEntity<Emp_Pojo> postEmp(@RequestBody Emp_Pojo emp_pojo) {
        System.out.println(emp_pojo.toString());
        repo.addNew(emp_pojo);
        return new ResponseEntity<Emp_Pojo>(emp_pojo, HttpStatus.CREATED);
    }
}