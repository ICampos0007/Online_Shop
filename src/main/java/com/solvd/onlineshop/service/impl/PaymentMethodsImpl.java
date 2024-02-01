package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.PaymentMethods;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.persistence.PaymentMethodsRepository;
import com.solvd.onlineshop.service.PaymentMethodService;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class PaymentMethodsImpl implements PaymentMethodService {
    private final PaymentMethodsRepository paymentMethodsRepository;
    private static final Logger logger = LogManager.getLogger(PaymentMethodsImpl.class);

    public PaymentMethodsImpl(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }


    @Override
    public void create(PaymentMethods paymentMethods) {
        try(SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
            paymentMethodsRepository.create(paymentMethods);
        }
    }

    @Override
    public Optional<PaymentMethods> findById(int id) {
        try(SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
           return paymentMethodsRepository.findById(id);
        }
    }

    @Override
    public void deleteById(int id) {
        try(SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
            paymentMethodsRepository.deleteById(id);
        }

    }
}
