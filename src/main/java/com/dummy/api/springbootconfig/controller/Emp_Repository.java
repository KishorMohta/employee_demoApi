package com.dummy.api.springbootconfig.controller;

import com.dummy.api.springbootconfig.Model.Emp_Pojo;
import java.util.LinkedList;
import java.util.List;

public class Emp_Repository {

List<Emp_Pojo> emp_Details;


    public Emp_Repository() {
        emp_Details = new LinkedList<Emp_Pojo>();

        Emp_Pojo emp=new Emp_Pojo();
        emp.setEmp_id(1);
        emp.setEmp_Age("26");
        emp.setEmp_Name("abc");
        emp.setEmp_Sal("12456789");

        Emp_Pojo emp1=new Emp_Pojo();
        emp1.setEmp_id(2);
        emp1.setEmp_Age("2");
        emp1.setEmp_Name("def");
        emp1.setEmp_Sal("987654321");

        emp_Details.add(emp);
        emp_Details.add(emp1);
    }
    public List<Emp_Pojo> getAllEmp(){
        return emp_Details;
    }
    public Emp_Pojo getEmp(int id){
        Emp_Pojo emp=null;
        for(Emp_Pojo emp_Data : emp_Details){
            if(emp_Data.getEmp_id()==id){
                return emp_Data;
            }
        }
        return null;
    }

    public void addNew(Emp_Pojo emp_pojo) {
        emp_Details.add(emp_pojo);
    }
}
