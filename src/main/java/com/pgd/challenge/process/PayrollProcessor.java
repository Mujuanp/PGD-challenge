package com.pgd.challenge.process;

import com.pgd.challenge.external.PayrollMemberProvider;
import com.pgd.challenge.model.Employee;

public class PayrollProcessor {

    private PayrollMemberProvider payrollMemberProvider;


    public double sumMonthlyAmount(final int payrollId) {
        return payrollMemberProvider.provideEmployeeList(payrollId).stream()
                .filter(Employee::isActive)
                .mapToDouble(employee -> {
                            failIfEmployeeHasNegativeAmount(employee);
                            failIfEmployeeHasZeroId(employee);
                            failIfEmployeeIsInvalid(employee);
                            return employee.getMonthlyAmount();
                        }
                ).sum();
    }

    private void failIfEmployeeHasNegativeAmount(final Employee employee) {
        if (employee.getMonthlyAmount() < 0) {
            throw new RuntimeException("The employee with ID: " + employee.getId() + " has a negative monthly amount.");
        }
    }

    private void failIfEmployeeHasZeroId(final Employee employee) {
        if (1 > employee.getId()) {
            throw new RuntimeException("There is an Employee with invalid ID.");
        }
    }

    private void failIfEmployeeIsInvalid(final Employee employee) {
        if (null == employee.getName() || employee.getName().isEmpty()) {
            throw new RuntimeException("The employee with ID: " + employee.getId() + " has a empty name.");
        }
    }

}
