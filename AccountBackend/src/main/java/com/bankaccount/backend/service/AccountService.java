package com.bankaccount.backend.service;

import com.bankaccount.backend.entity.AccountStatement;
import org.hibernate.Transaction;

import java.util.List;

public interface AccountService {
    void deposit (Long accountId, Double amount);
    void withdraw(Long accountId, Double amount) throws Exception;
    List<AccountStatement> getStatements(Long accountId);
}
