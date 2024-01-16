package com.solvd.onlineshop.dao.mybatisimpl;

import com.solvd.onlineshop.bin.Orders;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.persistence.OrdersRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrdersRepositoryMyBatisImpl implements OrdersRepository {

    private static final Logger logger = LogManager.getLogger(OrdersRepositoryMyBatisImpl.class);

    private final SqlSessionFactory sqlSessionFactory;

    public OrdersRepositoryMyBatisImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void create(Orders orders) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            OrdersRepository ordersRepository = sqlSession.getMapper(OrdersRepository.class);
            ordersRepository.create(orders);
            logger.info("Order created successfully: " + orders.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        try(SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            OrdersRepository ordersRepository = sqlSession.getMapper(OrdersRepository.class);
            ordersRepository.deleteById(id);
            logger.info("Order id has been deleted" + id);
        }
    }
}
