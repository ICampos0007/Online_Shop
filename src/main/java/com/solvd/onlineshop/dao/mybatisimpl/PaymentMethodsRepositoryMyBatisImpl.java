package com.solvd.onlineshop.dao.mybatisimpl;
import com.solvd.onlineshop.dao.DaoConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import com.solvd.onlineshop.bin.PaymentMethods;
import com.solvd.onlineshop.dao.persistence.PaymentMethodsRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class PaymentMethodsRepositoryMyBatisImpl implements PaymentMethodsRepository {
    private static final Logger logger = LogManager.getLogger(PaymentMethodsRepositoryMyBatisImpl.class);

    private final SqlSessionFactory sqlSessionFactory;

    public PaymentMethodsRepositoryMyBatisImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void create(PaymentMethods paymentMethods) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
            paymentMethodsRepository.create(paymentMethods);
            logger.info("Payment method created successfully: " + paymentMethods.getId());
        }
    }

    @Override
    public Optional<PaymentMethods> findById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
            return paymentMethodsRepository.findById(id);
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            PaymentMethodsRepository paymentMethodsRepository = sqlSession.getMapper(PaymentMethodsRepository.class);
            paymentMethodsRepository.deleteById(id);
            logger.info("Payment Method id has been deleted" + id);
        }
    }
}
