package com.bankaccount.backend.repository;

import com.bankaccount.backend.Dto.Operations;
import com.bankaccount.backend.entity.AccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<AccountStatement, Long> {
    List<AccountStatement> findByAccountId(Long accountId);
    List<AccountStatement> findByAccountIdAndType(Long accountId, Operations type);
}
