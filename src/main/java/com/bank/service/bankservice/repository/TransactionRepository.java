package com.bank.service.bankservice.repository;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Transaction;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("FROM Transaction WHERE accountFrom = :account OR accountTo = :account")
    List<Transaction> getAllByAccount(
            @Param("account") Account account,
            Pageable pageable
    );
}
