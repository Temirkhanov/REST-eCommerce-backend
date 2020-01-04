package com.goruslan.eCommerce.repository;

import com.goruslan.eCommerce.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
