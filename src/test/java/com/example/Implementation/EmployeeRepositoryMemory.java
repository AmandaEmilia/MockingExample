package com.example.Implementation;

import com.example.Employee;
import com.example.EmployeeManager;
import com.example.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryMemory implements EmployeeRepository {

    Map<String, Employee> employeeMap = new HashMap<>();

    public EmployeeRepositoryMemory(List<Employee> employees){

        for (int i = 0; i < employees.size(); i++) {
            employeeMap.put(employees.get(i).getId(),employees.get(i));
        }
    }


    @Override
    public List<Employee> findAll() {
        return employeeMap.values().stream().toList();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeMap.put(employee.getId(),employee);
    }
}
