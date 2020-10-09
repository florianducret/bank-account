package com.kata.bank.account.model.amount;

import com.kata.bank.account.model.exception.InvalidCurrencyException;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

public class Amount {

    @NonNull
    private final BigDecimal value;

    @NonNull
    private final Currency currency;

    @NonNull
    public BigDecimal getValue() {
        return value;
    }

    @NonNull
    public Currency getCurrency() {
        return currency;
    }

    public Amount(BigDecimal value, Currency currency) {
        this.currency = currency;
        this.value = value;
    }

    public Amount add(Amount amount){
        if(getCurrency() != amount.getCurrency()) {
            throw new InvalidCurrencyException("Cannot add two amount with different currency : " + amount.getCurrency() + " /= " + getCurrency());
        }
        return new Amount(value.add(amount.getValue()), getCurrency());
    }

    public Amount subtract(Amount amount){
        if(getCurrency() != amount.getCurrency()) {
            throw new InvalidCurrencyException("Cannot subtract two amount with different currency : " + amount.getCurrency() + " /= " + getCurrency());
        }
        return new Amount(value.subtract(amount.getValue()), getCurrency());
    }

}
