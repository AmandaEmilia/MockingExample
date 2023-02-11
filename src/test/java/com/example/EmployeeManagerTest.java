package com.example;

import com.example.TestDouble.BankServiceSpy;
import com.example.TestDouble.EmployeeRepositoryStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static com.example.TestUtil.getEmployees;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

public class EmployeeManagerTest {

    BankServiceSpy bankServiceSpy = new BankServiceSpy();

    EmployeeRepositoryStub employeeRepositoryStub = new EmployeeRepositoryStub();

    BankService bankServiceMock = Mockito.mock(BankService.class);

    EmployeeRepository employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);


    @Test
    void shouldPayEmployeeStub(){
        List<Employee> employeeList = getEmployees();
        EmployeeManager employeeManager = new EmployeeManager(employeeRepositoryStub, bankServiceSpy);

        int noOfPayments = employeeManager.payEmployees();

        assertEquals(noOfPayments,employeeList.size(),"No. of payments should equal to no. of employees");
    }

    @Test
    void shouldPayEmployeeWithValidationSpy(){
        List<Employee> employeeList = getEmployees();
        EmployeeManager employeeManager = new EmployeeManager(employeeRepositoryStub, bankServiceSpy);

        int noOfPayments = employeeManager.payEmployees();

        Assertions.assertEquals(noOfPayments, employeeList.size());

        Map<String, Double> sumToBePayedToEach = BankServiceSpy.getAmountPayedToEachEmployee();

        double expectedSalaryToBePayed = employeeList.stream()
                .mapToDouble(Employee -> Employee.getSalary())
                .sum();

        double actualSalaryToBePayed = sumToBePayedToEach.values().stream()
                .mapToDouble(d -> d)
                .sum();

        assertEquals(expectedSalaryToBePayed, actualSalaryToBePayed,"Expected salary should equal to acutal salary");
    }

    @Test
    void shouldPayEmployeeUsingMockito(){
        List<Employee> employees = getEmployees();

        Mockito.when(employeeRepositoryMock.findAll()).thenReturn(employees);
        Mockito.doNothing().when(bankServiceMock).pay(ArgumentMatchers.isA(String.class),ArgumentMatchers.isA(Double.class));

        EmployeeManager employeeManager = new EmployeeManager(employeeRepositoryMock, bankServiceMock);

        int noOfPayments = employeeManager.payEmployees();

        assertEquals(noOfPayments, employees.size(), "No. of payments should equal to no. of employees");
    }

}
