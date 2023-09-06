package com.transmission.loan.controller;

import com.transmission.loan.model.Loan;
import com.transmission.loan.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    @PostMapping("/saveLoan")
    public ResponseEntity<Object> saveLoan(@RequestBody Loan loan) throws Exception {
        try {
            logger.info("Inside loanController : saveLoan method()");
            Loan saveLoan = loanService.saveLoan(loan);
            return new ResponseEntity<>(saveLoan, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Error inside loanController : saveLoan method() " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/aggregatedLoan")
    public ResponseEntity<Object> aggregatedLoan() throws Exception {
        try {
            logger.info("Inside loanController : aggregatedLoan method()");
            List<Object[]> loanList = loanService.aggregatedLoan();
            return new ResponseEntity<>(loanList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error inside loanController : aggregatedLoan method() " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/checkDueDate")
    public ResponseEntity<String> checkDueDate(@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) throws Exception {
        try {
            logger.info("Inside loanController : checkDueDate method()");
            loanService.checkLoanDueDate(dueDate);
            if (LocalDate.now().isAfter(dueDate)) {
                return new ResponseEntity<>("Loan has crossed the due date", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("Loan due date checked.", HttpStatus.OK);
            }

        } catch (Exception ex) {
            logger.error("Error inside loanController : checkDueDate method() " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
