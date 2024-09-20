package com.amazon.ata.handlingexceptions;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bank {
    private Logger log = LogManager.getLogger(Bank.class);

    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) {
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            return true;
        } catch (InsufficientFundsException e) {
            log.error("Transfer failed due to insufficient funds: " + e.getMessage());
            return false;
        } catch (InvalidInputException e) {
            throw e; // propagate invalid input exceptions
        } catch (Exception e) {
            log.error("An unexpected error occurred during transfer: " + e.getMessage());
            return false;
        }
    }
}
