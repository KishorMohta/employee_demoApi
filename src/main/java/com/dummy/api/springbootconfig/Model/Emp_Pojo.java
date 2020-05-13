package com.dummy.api.springbootconfig.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emp_Pojo {
    private String emp_Name;
    private String emp_Age;
    private String emp_Sal;
    private int emp_id;

    public Emp_Pojo(String emp_Name, String emp_Age, String emp_Sal, int emp_id) {
        this.emp_Name = emp_Name;
        this.emp_Age = emp_Age;
        this.emp_Sal = emp_Sal;
        this.emp_id = emp_id;
    }

    public Emp_Pojo(){

    }



    @Override
    public String toString() {
        return "Emp_Pojo{" +
                "emp_Name='" + emp_Name + '\'' +
                ", emp_Age='" + emp_Age + '\'' +
                ", emp_Sal='" + emp_Sal + '\'' +
                '}';
    }
}