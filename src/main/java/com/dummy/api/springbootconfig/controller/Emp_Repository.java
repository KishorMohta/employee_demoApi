package com.dummy.api.springbootconfig.controller;

import com.dummy.api.springbootconfig.Model.Emp_Pojo;
import java.util.LinkedList;
import java.util.List;

public class Emp_Repository {

List<Emp_Pojo> emp_Details;


    public Emp_Repository() {
        emp_Details = new LinkedList<Emp_Pojo>();

       // (String emp_Name, String emp_Age, String emp_Sal, int emp_id)

        Emp_Pojo emp=new Emp_Pojo("abc","26","12344",1);
        Emp_Pojo emp1=new Emp_Pojo("def","2","556677",2);

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
