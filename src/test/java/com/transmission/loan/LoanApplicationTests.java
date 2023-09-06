package com.transmission.loan;

import com.transmission.loan.model.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LoanApplicationTests {

	@Test
	public void testLoanValidation() {

		Loan validLoan = new Loan();
		validLoan.setPaymentDate(LocalDate.now());
		validLoan.setDueDate(LocalDate.now().minusDays(1));
		assertThrows(IllegalArgumentException.class, validLoan::validateLoan);

	}

	@Test
	public void testLoanInValidation() {

		Loan invalidLoan = new Loan();
		invalidLoan.setPaymentDate(LocalDate.now());
		invalidLoan.setDueDate(LocalDate.now().minusDays(10));
		assertThrows(IllegalArgumentException.class, invalidLoan::validateLoan);

	}

}

