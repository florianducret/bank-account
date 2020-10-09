package com.kata.bank.account.services;

import com.kata.bank.account.data.AccountStore;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.amount.Amount;
import com.kata.bank.account.model.amount.Currency;
import com.kata.bank.account.model.exception.InvalidAccountOperationException;
import com.kata.bank.account.model.operation.AccountOperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountOperationServicesTest {

    @Mock
    private AccountStore accountStore;

    @InjectMocks
    private AccountOperationServices accountOperationServices;

    @Test
    void should_deposit_amount_in_account() {
        Account account = new Account(new Amount(BigDecimal.TEN, Currency.EURO));
        Mockito.when(accountStore.getAccount()).thenReturn(account);

        accountOperationServices.deposit(new Amount(BigDecimal.TEN, Currency.EURO));

        assertThat(account.getAmount()).isNotNull();
        assertThat(account.getAmount().getValue()).isEqualTo(BigDecimal.valueOf(20));
        assertThat(account.getAmount().getCurrency()).isEqualTo(Currency.EURO);
    }

    @Test
    void should_withdrawal_amount_in_account() {
        Account account = new Account(new Amount(BigDecimal.TEN, Currency.EURO));
        Mockito.when(accountStore.getAccount()).thenReturn(account);

        accountOperationServices.withdrawal(new Amount(BigDecimal.TEN, Currency.EURO));

        assertThat(account.getAmount()).isNotNull();
        assertThat(account.getAmount().getValue()).isEqualTo(BigDecimal.ZERO);
        assertThat(account.getAmount().getCurrency()).isEqualTo(Currency.EURO);
    }

    @Test()
    void should_throw_an_exception_if_withdrawal_an_amount_bigger_than_the_account_amount() {
        Account account = new Account(new Amount(BigDecimal.ONE, Currency.EURO));
        Mockito.when(accountStore.getAccount()).thenReturn(account);

        try {
            accountOperationServices.withdrawal(new Amount(BigDecimal.TEN, Currency.EURO));
        } catch (InvalidAccountOperationException ex) {
            assertThat(ex.getMessage()).isEqualTo("The operation " + AccountOperationType.WITHDRAWAL.name() + " is not possible on your account");
        }
    }

}
