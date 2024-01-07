package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.PaymentMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethodsRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(PaymentMethodsRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public PaymentMethodsRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
    }

    // Insert a new payment method
    public void addPaymentMethod(PaymentMethods paymentMethod) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO paymentmethods (id, user_id, card_number, expiration_date, cvv) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, paymentMethod.getId());
                preparedStatement.setInt(2, paymentMethod.getUser_id());
                preparedStatement.setString(3, paymentMethod.getCard_Number());
                preparedStatement.setInt(4, paymentMethod.getExpiration_Date());
                preparedStatement.setInt(5, paymentMethod.getCvv());
                preparedStatement.executeUpdate();
                logger.info("Payment method created successfully: " + paymentMethod.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating payment method: " + paymentMethod.getId(), e);
        }
    }

    // Retrieve a payment method by ID
    public PaymentMethods getPaymentMethodById(int paymentMethodId) {
        PaymentMethods paymentMethod = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM paymentmethods WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
            }
        } catch (SQLException e) {
            logger.error("Error retrieving payment method: " + paymentMethodId, e);
        }
        return paymentMethod;
    }

    // Update payment method information
    public void updatePaymentMethod(PaymentMethods paymentMethod) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE paymentmethods SET user_id = ?, card_number = ?, " +
                    "expiration_date = ?, cvv = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, paymentMethod.getUser_id());
                preparedStatement.setString(2, paymentMethod.getCard_Number());
                preparedStatement.setInt(3, paymentMethod.getExpiration_Date());
                preparedStatement.setInt(4, paymentMethod.getCvv());
                preparedStatement.setInt(5, paymentMethod.getId());
                preparedStatement.executeUpdate();
                logger.info("Payment method updated successfully: " + paymentMethod.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating payment method: " + paymentMethod.getId(), e);
        }
    }

    // Retrieve a payment method by User ID
    public PaymentMethods getPaymentMethodByUserId(int userId) {
        PaymentMethods paymentMethod = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM paymentmethods WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
            }
        } catch (SQLException e) {
            logger.error("Error retrieving payment method by user ID: " + userId, e);
        }
        return paymentMethod;
    }

    // Retrieve a payment method by Card Number
    public PaymentMethods getPaymentMethodByCardNumber(String cardNumber) {
        PaymentMethods paymentMethod = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM paymentmethods WHERE card_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
            }
        } catch (SQLException e) {
            logger.error("Error retrieving payment method by card number: " + cardNumber, e);
        }
        return paymentMethod;
    }
}
