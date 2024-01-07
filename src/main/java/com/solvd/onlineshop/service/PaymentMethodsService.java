package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.PaymentMethods;
import com.solvd.onlineshop.dao.PaymentMethodsRepositoryImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class PaymentMethodsService {

    private static final Logger logger = LogManager.getLogger(PaymentMethodsService.class);
    private PaymentMethodsRepositoryImpl paymentMethodsRepository;

    public PaymentMethodsService(SqlSessionFactory sqlSessionFactory) {
        this.paymentMethodsRepository = new PaymentMethodsRepositoryImpl(sqlSessionFactory);
    }

    // Create a new payment method for a user
    public void createPaymentMethod(int id, int userId, String cardNumber, int expirationDate, int cvv) throws SQLException {
        // Check if the card number already exists
        PaymentMethods existingPaymentMethod = paymentMethodsRepository.getPaymentMethodByCardNumber(cardNumber);

        if (existingPaymentMethod == null) {
            // Card number does not exist, create a new payment method
            PaymentMethods newPaymentMethod = new PaymentMethods(id, userId, cardNumber, expirationDate, cvv);

            // Create the new payment method
            paymentMethodsRepository.addPaymentMethod(newPaymentMethod);
            logger.info("Payment method created successfully for user ID: " + userId);
        } else {
            // Card number already exists
            logger.error("Card number '" + cardNumber + "' already exists for user ID: " + userId);
        }
    }
}
