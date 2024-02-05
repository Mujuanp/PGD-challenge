package com.pgd.challenge.process;

import com.pgd.challenge.external.PayrollMemberProvider;
import com.pgd.challenge.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PayrollProcessorTest {
    private static final int PAYROLL_ID = 1;
    @Mock
    private PayrollMemberProvider payrollMemberProvider;
    @InjectMocks
    private PayrollProcessor payrollProcessor;

    @Test
    void testMonthlyAmountIsNegativeAndException() {
        // Given
        final List<Employee> expectedEployeeList = List.of(
                new Employee(1, "Bob", -1600f, true),
                new Employee(2, "Mary", 200f, true),
                new Employee(3, "John", 200f, true),
                new Employee(4, "Paul", 200f, true),
                new Employee(5, "Ringo", 200f, true),
                new Employee(6, "George", 200f, true)

        );
        final String expectedExceptionMessage = "The employee with ID: 1 has a negative monthly amount.";

        // When
        when(payrollMemberProvider.provideEmployeeList(PAYROLL_ID)).thenReturn(expectedEployeeList);

        final RuntimeException resultException = assertThrows(RuntimeException.class, () ->
                payrollProcessor.sumMonthlyAmount(PAYROLL_ID)
        );

        // Then
        assertEquals(expectedExceptionMessage, resultException.getMessage());
        verify(payrollMemberProvider).provideEmployeeList(PAYROLL_ID);
        verifyNoMoreInteractions(payrollMemberProvider);
    }

    @Test
    void testZeroEmployeeIdAndException() {
        // Given
        final List<Employee> expectedEployeeList = List.of(
                new Employee(0, "Null", 100, true)
        );
        final String expectedExceptionMessage = "There is an Employee with invalid ID.";

        // When
        when(payrollMemberProvider.provideEmployeeList(PAYROLL_ID)).thenReturn(expectedEployeeList);

        final RuntimeException resultException = assertThrows(RuntimeException.class, () ->
                payrollProcessor.sumMonthlyAmount(PAYROLL_ID)
        );

        // Then
        assertEquals(expectedExceptionMessage, resultException.getMessage());
        verify(payrollMemberProvider).provideEmployeeList(PAYROLL_ID);
        verifyNoMoreInteractions(payrollMemberProvider);
    }

    @Test
    void testEmptyNameEmployeeAndException() {
        // Given
        final List<Employee> expectedEployeeList = List.of(
                new Employee(1, "", 100, true)
        );
        final String expectedExceptionMessage = "The employee with ID: 1 has a empty name.";

        // When
        when(payrollMemberProvider.provideEmployeeList(PAYROLL_ID)).thenReturn(expectedEployeeList);

        final RuntimeException resultException = assertThrows(RuntimeException.class, () ->
                payrollProcessor.sumMonthlyAmount(PAYROLL_ID)
        );

        // Then
        assertEquals(expectedExceptionMessage, resultException.getMessage());
        verify(payrollMemberProvider).provideEmployeeList(PAYROLL_ID);
        verifyNoMoreInteractions(payrollMemberProvider);
    }

    @Test
    void testNullNameEmployeeAndException() {
        // Given
        final List<Employee> expectedEployeeList = List.of(
                new Employee(1, null, 100, true)
        );
        final String expectedExceptionMessage = "The employee with ID: 1 has a empty name.";

        // When
        when(payrollMemberProvider.provideEmployeeList(PAYROLL_ID)).thenReturn(expectedEployeeList);

        final RuntimeException resultException = assertThrows(RuntimeException.class, () ->
                payrollProcessor.sumMonthlyAmount(PAYROLL_ID)
        );

        // Then
        assertEquals(expectedExceptionMessage, resultException.getMessage());
        verify(payrollMemberProvider).provideEmployeeList(PAYROLL_ID);
        verifyNoMoreInteractions(payrollMemberProvider);
    }

    @Test
    void testEmployeesAreValidAndGetTotalAmount() {
        // Given
        final List<Employee> expectedEployeeList = List.of(
                new Employee(1, "Bob", Float.MAX_VALUE, true),
                new Employee(2, "Lin", Float.MAX_VALUE, true)
        );
        final double expectedTotalAmount = Double.sum(Float.MAX_VALUE, Float.MAX_VALUE);

        // When
        when(payrollMemberProvider.provideEmployeeList(PAYROLL_ID)).thenReturn(expectedEployeeList);

        final double totalAmount = payrollProcessor.sumMonthlyAmount(PAYROLL_ID);

        // Then
        assertEquals(expectedTotalAmount, totalAmount);
        verify(payrollMemberProvider).provideEmployeeList(PAYROLL_ID);
        verifyNoMoreInteractions(payrollMemberProvider);
    }


    @Test
    void testIgnoreInactiveEmployeesAndGetTotalAmount() {
        // Given
        final List<Employee> expectedEployeeList = List.of(
                new Employee(1, "Bob", Float.MAX_VALUE, true),
                new Employee(2, null, Float.MAX_VALUE, false),
                new Employee(3, "", Float.MAX_VALUE, false),
                new Employee(0, "Fern", Float.MAX_VALUE, false),
                new Employee(6, "Jake", Float.MAX_VALUE, false),
                new Employee(5, "Fin", Float.MIN_VALUE, false)
        );

        // When
        when(payrollMemberProvider.provideEmployeeList(PAYROLL_ID)).thenReturn(expectedEployeeList);

        final double totalAmount = payrollProcessor.sumMonthlyAmount(PAYROLL_ID);

        // Then
        assertEquals(Float.MAX_VALUE, totalAmount);
        verify(payrollMemberProvider).provideEmployeeList(PAYROLL_ID);
        verifyNoMoreInteractions(payrollMemberProvider);
    }
}
