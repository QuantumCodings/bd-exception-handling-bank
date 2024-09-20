package com.amazon.ata.handlingexceptions;

import java.math.BigDecimal;

import com.amazon.ata.handlingexceptions.exceptions.InsufficientFundsException;

public class CheckingAccount implements BankAccount {
    
    private String accountId;
    private BigDecimal balance;
    private Validator validator = new Validator();

    public CheckingAccount(String accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        if (validator.validate(amount)) {
            balance = balance.add(amount);
        }
        return balance;
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        if (!validator.validate(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        balance = balance.subtract(amount);
        return balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}
