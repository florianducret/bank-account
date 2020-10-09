package com.kata.bank.account.model.exception;

import com.kata.bank.account.model.amount.Amount;
import com.kata.bank.account.model.operation.AccountOperationType;

import java.math.BigDecimal;

public class InvalidAccountOperationException extends RuntimeException {

    public InvalidAccountOperationException(AccountOperationType accountOperationType, Amount amount) {
        super("The operation " + accountOperationType.name() + " with the amount of " + amount.getValue() + amount.getCurrency().getSymbol() + " is not possible on your account");
    }

}
