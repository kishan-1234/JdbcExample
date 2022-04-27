package dao;

import database.driver.Driver;
import models.Transaction;

import java.sql.*;

public class TransactionDaoImpl implements TransactionDao {

    private Driver driver = Driver.getInstance();

    private static final String INSERT_TRANSACTION = "INSERT INTO Transaction(timestamp,userId,currency,amount) VALUES(?,?,?,?)";
    private static final String SELECT_TRANSACTION = "SELECT id,timestamp,userId,currency,amount FROM Transaction where id = ?";

    @Override
    public Long AddTransaction(Transaction transaction) {
        Long transactionId = null;
        try {
            Connection connection = driver.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, transaction.getTimestamp());
            preparedStatement.setLong(2,transaction.getUserId());
            preparedStatement.setString(3,transaction.getCurrency().toString());
            preparedStatement.setDouble(4,transaction.getAmount());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                transactionId=rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  transactionId;
    }

    @Override
    public Transaction GetTransaction(Long transactionId) {

        Transaction transaction = null;
        try {
            Connection connection = driver.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTION);
            preparedStatement.setLong(1, transactionId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                Long userId = rs.getLong("userId");
                Transaction.Currency currency = Transaction.Currency.valueOf(rs.getString("currency"));
                Double amount = rs.getDouble("amount");
                transaction = new Transaction();
                transaction.setId(id);
                transaction.setTimestamp(timestamp);
                transaction.setUserId(userId);
                transaction.setCurrency(currency);
                transaction.setAmount(amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }
}
