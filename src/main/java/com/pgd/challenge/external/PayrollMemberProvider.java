package com.pgd.challenge.external;

import com.pgd.challenge.model.Employee;

import java.util.List;

public interface PayrollMemberProvider {

    List<Employee> provideEmployeeList(int payrollId);
}
