package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.PaymentMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;

public class PaymentMethodsRepositoryImpl {
    private static final String CREATE_PAYMENT_METHOD = "INSERT INTO paymentmethods (user_id, card_number, expiration_date, cvv) " +
            "VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM paymentmethods WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE paymentmethods SET user_id = ?, card_number = ?, " +
            "expiration_date = ?, cvv = ? WHERE id = ?";
    private static final String GET_BY_USER_ID = "SELECT * FROM paymentmethods WHERE user_id = ?";
    private static final String GET_BY_CARD_NUMBER = "SELECT * FROM paymentmethods WHERE card_number = ?";


    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(PaymentMethodsRepositoryImpl.class);

    public PaymentMethodsRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
    }

    // Insert a new payment method
    public void addPaymentMethod(PaymentMethods paymentMethod) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_PAYMENT_METHOD,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, paymentMethod.getUser_id());
            preparedStatement.setString(2, paymentMethod.getCard_Number());
            preparedStatement.setInt(3, paymentMethod.getExpiration_Date());
            preparedStatement.setInt(4, paymentMethod.getCvv());
            preparedStatement.executeUpdate();
            logger.info("Payment method created successfully: " + paymentMethod.getId());
        } catch (SQLException e) {
            logger.error("Error creating payment method: " + paymentMethod.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a payment method by ID
    public PaymentMethods getPaymentMethodById(int paymentMethodId) {
        Connection connection = ConnectionPool.getConnection();
        PaymentMethods paymentMethod = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
            preparedStatement.setInt(1, paymentMethodId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paymentMethod = new PaymentMethods(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("card_number"),
                            resultSet.getInt("expiration_date"),
                            resultSet.getInt("cvv")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving payment method: " + paymentMethodId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return paymentMethod;
    }

    // Update payment method information
    public void updatePaymentMethod(PaymentMethods paymentMethod) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setInt(1, paymentMethod.getUser_id());
            preparedStatement.setString(2, paymentMethod.getCard_Number());
            preparedStatement.setInt(3, paymentMethod.getExpiration_Date());
            preparedStatement.setInt(4, paymentMethod.getCvv());
            preparedStatement.setInt(5, paymentMethod.getId());
            preparedStatement.executeUpdate();
            logger.info("Payment method updated successfully: " + paymentMethod.getId());
        } catch (SQLException e) {
            logger.error("Error updating payment method: " + paymentMethod.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a payment method by User ID
    public PaymentMethods getPaymentMethodByUserId(int userId) {
        Connection connection = ConnectionPool.getConnection();
        PaymentMethods paymentMethod = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID)
        ) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paymentMethod = new PaymentMethods(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("card_number"),
                            resultSet.getInt("expiration_date"),
                            resultSet.getInt("cvv")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving payment method by user ID: " + userId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return paymentMethod;
    }

    // Retrieve a payment method by Card Number
    public PaymentMethods getPaymentMethodByCardNumber(String cardNumber) {
        Connection connection = ConnectionPool.getConnection();
        PaymentMethods paymentMethod = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CARD_NUMBER)
        ) {
            preparedStatement.setString(1, cardNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paymentMethod = new PaymentMethods(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("card_number"),
                            resultSet.getInt("expiration_date"),
                            resultSet.getInt("cvv")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving payment method by card number: " + cardNumber, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return paymentMethod;
    }
}
