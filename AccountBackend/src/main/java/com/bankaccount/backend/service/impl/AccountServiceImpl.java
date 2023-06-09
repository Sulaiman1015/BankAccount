package com.bankaccount.backend.service.impl;

import com.bankaccount.backend.repository.AccountRepository;
import com.bankaccount.backend.repository.TransactionRepository;
import com.bankaccount.backend.entity.Account;
import com.bankaccount.backend.entity.AccountStatement;
import com.bankaccount.backend.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    @Override
    public void deposit(Long accountId, Double amount) {
        Account account = accountRepository.findByAccountId(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        Double balance = account.getBalance() + amount;
        account.setBalance(balance);
        AccountStatement statement = new AccountStatement(null, LocalDateTime.now(), amount, balance, account);
        transactionRepository.save(statement);
    }

    @Transactional
    @Override
    public void withdraw(Long accountId, Double amount) throws Exception {
        Account account = accountRepository.findByAccountId(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        Double balance = account.getBalance() - amount;
        if (balance < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(balance);
        AccountStatement transaction = new AccountStatement(null, LocalDateTime.now(), -amount, balance, account);
        transactionRepository.save(transaction);
    }

    @Override
    public List<AccountStatement> getStatements(Long accountId) {
        Account account = accountRepository.findByAccountId(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getStatements();
    }

}
