package com.kata.bank.account.data;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.amount.Amount;
import com.kata.bank.account.model.amount.Currency;
import com.kata.bank.account.model.operation.DepositOperation;
import com.kata.bank.account.model.operation.WithdrawalOperation;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public class AccountStore {

    public Account getAccount() {
        Account account = new Account(new Amount(BigDecimal.TEN, Currency.EURO));
        account.addOperation(new DepositOperation(LocalDateTime.of(2020, 10,2, 10,10),
                new Amount(BigDecimal.valueOf(20), Currency.EURO),
                new Amount(BigDecimal.ZERO, Currency.EURO),
                new Amount(BigDecimal.valueOf(20), Currency.EURO)));

        account.addOperation(new WithdrawalOperation(LocalDateTime.of(2020, 10,5, 20,32),
                new Amount(BigDecimal.TEN, Currency.EURO),
                new Amount(BigDecimal.valueOf(20), Currency.EURO),
                new Amount(BigDecimal.TEN, Currency.EURO)));

        return account;
    }

    public Account getAnotherAccount() {
        return new Account(new Amount(BigDecimal.valueOf(100d), Currency.DOLLAR));
    }

}
