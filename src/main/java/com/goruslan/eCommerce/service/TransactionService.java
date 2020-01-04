package com.goruslan.eCommerce.service;

import com.goruslan.eCommerce.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    Long numberOfTransactions();

    List<Transaction> findAllTransactions();
}
