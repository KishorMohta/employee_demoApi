package com.dummy.api.springbootconfig.Model;

public class Emp_Pojo {
     private String emp_Name;

    private String emp_Age;
     private String emp_Sal;
    private int emp_id;


    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }


    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public String getEmp_Age() {
        return emp_Age;
    }

    public void setEmp_Age(String emp_Age) {
        this.emp_Age = emp_Age;
    }

    public String getEmp_Sal() {
        return emp_Sal;
    }

    public void setEmp_Sal(String emp_Sal) {
        this.emp_Sal = emp_Sal;
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