package service;

import dao.TransactionDao;
import dao.TransactionDaoImpl;
import dao.TransactionInMemoryDaoImpl;
import models.Transaction;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionService {

    private static TransactionService transactionService = null;

    private TransactionService() {}

    public static TransactionService getInstance() {
        if(transactionService==null) {
            synchronized (TransactionService.class) {
                if(transactionService==null) {
                    transactionService = new TransactionService();
                }
            }
        }
        return transactionService;
    }

    private TransactionDao transactionDao = new TransactionDaoImpl();

    public Long AddTransaction(String timestamp,String userId,String currency,String amount) {

        Long transactionId = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(timestamp);
            Timestamp timestampSQL = new java.sql.Timestamp(parsedDate.getTime());
            Long userIdSQL = Long.parseLong(userId);
            Transaction.Currency currencySQL = Transaction.Currency.valueOf(currency);
            Double amountSQL = Double.parseDouble(amount);
            Transaction transaction = new Transaction();
            transaction.setTimestamp(timestampSQL);
            transaction.setUserId(userIdSQL);
            transaction.setCurrency(currencySQL);
            transaction.setAmount(amountSQL);
            transactionId = transactionDao.AddTransaction(transaction);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return transactionId;
    }

    public Transaction GetTransaction(Long transactionId) {

        return transactionDao.GetTransaction(transactionId);
    }
}
