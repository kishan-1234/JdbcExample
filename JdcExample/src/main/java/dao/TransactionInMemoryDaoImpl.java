package dao;

import models.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionInMemoryDaoImpl implements TransactionDao {

    Map<Long,Transaction> transactionMap = new HashMap();
    private AtomicLong id = new AtomicLong(0);

    @Override
    public Transaction GetTransaction(Long transactionId) {
        return transactionMap.get(transactionId);
    }

    @Override
    public Long AddTransaction(Transaction transaction) {
        Long Id = id.incrementAndGet();
        transaction.setId(Id);
        try {
            transactionMap.put(Id,transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Id;
    }
}
