package com.solvd.onlineshop.service.mybatisservice;

import com.solvd.onlineshop.bin.PaymentMethods;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.persistence.PaymentMethodsRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class PaymentMethodsMyBatisService {
    private final PaymentMethodsRepository paymentMethodsRepository;
    private static final Logger logger = LogManager.getLogger(PaymentMethodsMyBatisService.class);

    public PaymentMethodsMyBatisService(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

    public void createPaymentMethod(PaymentMethods paymentMethods) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
            paymentMethodsRepository.create(paymentMethods);
        }
    }

    public Optional<PaymentMethods> findPaymentMethodById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
            return paymentMethodsRepository.findById(id);
        }
    }

    public void deletePaymentMethod(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);

            Optional<PaymentMethods> existingPaymentMethod = paymentMethodsRepository.findById(id);

            if (existingPaymentMethod.isPresent()) {
                // Payment method exists, proceed with deletion
                paymentMethodsRepository.deleteById(id);
                logger.info("Payment method with ID {} has been deleted", id);
            } else {
                // Payment method with the specified ID not found, log an error
                logger.error("Cannot delete payment method with ID {}. Payment method not found", id);
            }
        }
    }
}
