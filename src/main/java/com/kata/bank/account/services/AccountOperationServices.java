package com.kata.bank.account.services;

import com.kata.bank.account.data.AccountStore;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.amount.Amount;
import com.kata.bank.account.model.exception.InvalidAccountOperationException;
import com.kata.bank.account.model.operation.AccountOperation;
import com.kata.bank.account.model.operation.AccountOperationType;
import com.kata.bank.account.model.operation.DepositOperation;
import com.kata.bank.account.model.operation.WithdrawalOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class AccountOperationServices {

    @Autowired
    private AccountStore accountStore;

    public Amount deposit(Amount amount) {
        Account account = accountStore.getAccount();
        Amount previousAmount = account.getAmount();
        Amount currentAmount = account.getAmount().add(amount);
        account.setAmount(currentAmount);
        account.addOperation(new DepositOperation(LocalDateTime.now(), amount, previousAmount, currentAmount));
        return account.getAmount();
    }

    public Amount withdrawal(Amount amount) throws InvalidAccountOperationException {
        Account account = accountStore.getAccount();
        BigDecimal newValue = account.getAmount().getValue().subtract(amount.getValue());
        // on verfie que l'on peut retirer l'argent sans dépasser le plafond
        if(newValue.signum() == -1 && newValue.abs().doubleValue() > Account.AUTHORIZED_OVERDRAFT) {
            throw new InvalidAccountOperationException(AccountOperationType.WITHDRAWAL, amount);
        }
        Amount previousAmount = account.getAmount();
        Amount currentAmount = account.getAmount().subtract(amount);
        account.setAmount(currentAmount);
        account.addOperation(new WithdrawalOperation(LocalDateTime.now(), amount, previousAmount, currentAmount));
        return account.getAmount();
    }

    public List<AccountOperation> getHistory() {
        Account account = accountStore.getAccount();
        return account.getHistory();
    }

}
