package dao;

import models.Transaction;

public interface TransactionDao {

    Transaction GetTransaction(Long transactionId);
    Long AddTransaction(Transaction transaction);
}
