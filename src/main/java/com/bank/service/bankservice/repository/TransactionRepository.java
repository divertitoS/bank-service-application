package com.bank.service.bankservice.repository;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "FROM Transaction WHERE accountFrom = :account OR accountTo = :account")
    List<Transaction> getAllByAccount(
            @Param("account") Account account,
            Pageable pageable,
            Sort sort
    );
}
