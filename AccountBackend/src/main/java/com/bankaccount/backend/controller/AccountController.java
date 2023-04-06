package com.bankaccount.backend.controller;

import com.bankaccount.backend.Dto.Operations;
import com.bankaccount.backend.Dto.TransactionDto;
import com.bankaccount.backend.entity.AccountStatement;
import com.bankaccount.backend.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    //deposit Method
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable Long accountId, @RequestBody @Valid TransactionDto transactionDto) {
        if (transactionDto.getType() != Operations.DEPOSIT) {
            return ResponseEntity.badRequest().build();
        }
        accountService.deposit(accountId, transactionDto.getAmount());
        return ResponseEntity.ok().build();
    }

    //withdraw method
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable Long accountId, @RequestBody @Valid TransactionDto transactionDto) {
        if (transactionDto.getType() != Operations.WITHDRAWAL) {
            return ResponseEntity.badRequest().build();
        }
        accountService.withdraw(accountId, transactionDto.getAmount());
        return ResponseEntity.ok().build();
    }

    //get account statement method
    @GetMapping("/{accountId}/statements")
    public ResponseEntity<List<AccountStatement>> getAccountStatement(@PathVariable Long accountId){
        return ResponseEntity.ok(accountService.getStatements(accountId));
    }

}
