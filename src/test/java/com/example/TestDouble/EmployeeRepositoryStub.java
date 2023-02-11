package com.example.TestDouble;

import com.example.Employee;
import com.example.EmployeeRepository;

import java.util.List;

import static com.example.TestUtil.getEmployees;

public class EmployeeRepositoryStub implements EmployeeRepository {
    @Override
    public List<Employee> findAll() {
        return getEmployees();
    }

    @Override
    public Employee save(Employee e) {
        return e;
    }
}

