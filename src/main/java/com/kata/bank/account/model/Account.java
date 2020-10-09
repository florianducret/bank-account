package com.kata.bank.account.model;

import com.kata.bank.account.model.amount.Amount;
import com.kata.bank.account.model.operation.AccountOperation;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private Amount amount;

    private List<AccountOperation> history;

    //plafond de decouvert
    public static final int AUTHORIZED_OVERDRAFT = 20;

    public Account(Amount amount) {
        this.amount = amount;
        this.history = new ArrayList<>();
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public List<AccountOperation> getHistory() {
        if(history == null) {
            return new ArrayList<>();
        }
        return history;
    }

    public void addOperation(AccountOperation operation) {
        getHistory().add(operation);
    }
}
