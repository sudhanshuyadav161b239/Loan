package com.transmission.loan.repository;

import com.transmission.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT l.customerId, l.lenderId, SUM(l.remainingAmount) AS totalRemainingAmount, " +
            "SUM(l.interestPerDay) AS totalInterestAmount, SUM(l.penaltyPerDay) AS totalPenaltyAmount " +
            "FROM Loan l " +
            "GROUP BY l.customerId, l.lenderId, l.interestPerDay")
    List<Object[]> aggregateLoanData();

}
