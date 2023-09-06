package com.transmission.loan.service;

import com.transmission.loan.model.Loan;
import com.transmission.loan.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    LoanRepository loanRepository;

    public Loan saveLoan(Loan loan) throws Exception {
        logger.info("Inside LoanService class : saveLoan method()");
        if (Objects.isNull(loan.getPaymentDate())) {
            throw new Exception("PaymentDate is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getDueDate())) {
            throw new Exception("DueDate is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getAmount())) {
            throw new Exception("Amount is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getInterestPerDay())) {
            throw new Exception("InterestPerDay is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getRemainingAmount())) {
            throw new Exception("RemainingAmount is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getPenaltyPerDay())) {
            throw new Exception("PenaltyPerDay is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getLoanId())) {
            throw new Exception("LoanID is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getCustomerId())) {
            throw new Exception("CustomerId is mandatory field it can not be null or empty.");
        }
        if (Objects.isNull(loan.getLenderId())) {
            throw new Exception("LenderId() is mandatory field it can not be null or empty.");
        }
        if (loan.getPaymentDate().isAfter(loan.getDueDate())) {
            throw new IllegalArgumentException("Payment date cannot be greater than due date");
        }
        return loanRepository.save(loan);
    }

    public List<Object[]> aggregatedLoan() throws Exception {
        List<Object[]> loanList = loanRepository.aggregateLoanData();
        return loanList;
    }

    public void checkLoanDueDate(LocalDate dueDate) {
        if (LocalDate.now().isAfter(dueDate)) {
            // Loan has crossed the due date, log an alert
            logger.warn("Loan has crossed the due date. Due Date: {}, Current Date: {}", dueDate, LocalDate.now());
        }
    }
}
