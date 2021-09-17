package com.desafio.dock.repositories;

import com.desafio.dock.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByIdTransaction(long idTransaction);
}
