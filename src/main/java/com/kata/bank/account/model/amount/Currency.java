package com.kata.bank.account.model.amount;

public enum Currency {

    EURO("€"),
    DOLLAR("$");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
