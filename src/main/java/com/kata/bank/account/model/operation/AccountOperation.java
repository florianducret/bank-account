package com.kata.bank.account.model.operation;

import com.fasterxml.jackson.annotation.JsonView;
import com.kata.bank.account.model.amount.Amount;

import java.time.LocalDateTime;

public abstract class AccountOperation {

    private LocalDateTime date;

    private Amount amountOperation;

    private Amount previousAccountAmount;

    private Amount currentAccountAmount;

    public AccountOperation(LocalDateTime date, Amount amountOperation, Amount previousAccountAmount, Amount currentAccountAmount) {
        this.date = date;
        this.amountOperation = amountOperation;
        this.previousAccountAmount = previousAccountAmount;
        this.currentAccountAmount = currentAccountAmount;
    }

    @JsonView
    public abstract AccountOperationType getAccountOperationType();

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Amount getAmountOperation() {
        return amountOperation;
    }

    public void setAmountOperation(Amount amountOperation) {
        this.amountOperation = amountOperation;
    }

    public Amount getPreviousAccountAmount() {
        return previousAccountAmount;
    }

    public void setPreviousAccountAmount(Amount previousAccountAmount) {
        this.previousAccountAmount = previousAccountAmount;
    }

    public Amount getCurrentAccountAmount() {
        return currentAccountAmount;
    }

    public void setCurrentAccountAmount(Amount currentAccountAmount) {
        this.currentAccountAmount = currentAccountAmount;
    }
}
