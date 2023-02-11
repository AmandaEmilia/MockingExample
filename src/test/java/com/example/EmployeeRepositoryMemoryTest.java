package com.example;

import com.example.Implementation.EmployeeRepositoryMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.TestUtil.getEmployees;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRepositoryMemoryTest {

    @Test
    void shouldFindAllEmployees() {
        List<Employee> employees = getEmployees();

        EmployeeRepositoryMemory employeeRepositoryMemory = new EmployeeRepositoryMemory(employees);

        assertEquals(employeeRepositoryMemory.findAll().size(), employees.size());

    }

    @Test
    void shouldFindAllEmployeesWhenAddingOne() {
        List<Employee> employees = getEmployees();

        EmployeeRepositoryMemory employeeRepositoryMemory = new EmployeeRepositoryMemory(employees);
        assertEquals(employeeRepositoryMemory.findAll().size(), employees.size());

        employeeRepositoryMemory.save(new Employee("456", 10000));

        assertEquals(employeeRepositoryMemory.findAll().size(), employees.size() + 1);
    }

    @Test
    void shouldSaveAddedEmployee() {
        Employee Amanda = new Employee("88", 15000);
        double newSalary = 25000;

        EmployeeRepositoryMemory employeeRepositoryMemory = new EmployeeRepositoryMemory(List.of());
        assertEquals(employeeRepositoryMemory.findAll().size(), 0);

        employeeRepositoryMemory.save(Amanda);
        assertEquals(employeeRepositoryMemory.findAll().size(), 1);

        employeeRepositoryMemory.save(new Employee(Amanda.getId(), newSalary));
        assertEquals(employeeRepositoryMemory.findAll().size(), 1);

        Employee newEmployeeWithSameId = employeeRepositoryMemory.findAll()
                .stream()
                .filter(employee -> employee.getId().equalsIgnoreCase(Amanda.getId()))
                .findFirst()
                .get();

        assertEquals(newEmployeeWithSameId.getSalary(), newSalary);
        assertEquals(newEmployeeWithSameId.getId(), Amanda.getId());
    }

}
