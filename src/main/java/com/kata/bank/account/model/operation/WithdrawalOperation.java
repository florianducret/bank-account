package com.kata.bank.account.model.operation;

import com.kata.bank.account.model.amount.Amount;

import java.time.LocalDateTime;

public class WithdrawalOperation extends AccountOperation {

    public WithdrawalOperation(LocalDateTime date, Amount amountOperation, Amount previousAccountAmount, Amount currentAccountAmount) {
        super(date, amountOperation, previousAccountAmount, currentAccountAmount);
    }

    @Override
    public AccountOperationType getAccountOperationType() {
        return AccountOperationType.WITHDRAWAL;
    }
}
