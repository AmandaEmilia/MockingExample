package com.example.TestDouble;

import com.example.BankService;

import java.util.HashMap;
import java.util.Map;

public class BankServiceSpy implements BankService {

    private static Map<String, Double> amountPayedToEachEmployee = new HashMap<>();
    @Override
    public void pay(String id, double amount) {
        amountPayedToEachEmployee.put(id, amount);

    }

    public static Map<String,Double> getAmountPayedToEachEmployee(){
        System.out.println(amountPayedToEachEmployee.toString());
        return amountPayedToEachEmployee;
    }
}

