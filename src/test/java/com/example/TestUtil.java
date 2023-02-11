package com.example;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static List<Employee> getEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("11",10000));
        employees.add(new Employee("22",20000));
        employees.add(new Employee("33",30000));
        return employees;
    }

}
