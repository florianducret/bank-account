package com.kata.bank.account.view.api;

import com.kata.bank.account.model.amount.Amount;
import com.kata.bank.account.model.exception.InvalidAccountOperationException;
import com.kata.bank.account.model.exception.InvalidCurrencyException;
import com.kata.bank.account.model.operation.AccountOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kata.bank.account.services.AccountOperationServices;

import java.util.List;

@RestController
@RequestMapping("/accountOperation")
public class AccountOperationApi {

    @Autowired
    private AccountOperationServices accountOperationServices;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Amount amount) {
        Amount newAmount = accountOperationServices.deposit(amount);
        return ResponseEntity.ok("Votre nouveau montant est " + newAmount.getValue() + newAmount.getCurrency().getSymbol());
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@RequestBody Amount amount) {
        Amount newAmount = accountOperationServices.withdrawal(amount);
        return ResponseEntity.ok("Votre nouveau montant est " + newAmount.getValue() + newAmount.getCurrency().getSymbol());
    }

    @GetMapping("/history")
    public ResponseEntity<List<AccountOperation>> history() {
        return ResponseEntity.ok(accountOperationServices.getHistory());
    }

    @ExceptionHandler({InvalidAccountOperationException.class, InvalidCurrencyException.class})
    public ResponseEntity<String> InvalidAccountOperationHandler(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
