package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.Orders;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.OrdersRepositoryImpl;
import com.solvd.onlineshop.dao.persistence.OrdersRepository;
import com.solvd.onlineshop.service.OrderService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrdersServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrdersServiceImpl.class);
    private final SqlSessionFactory sqlSessionFactory;
    private OrdersRepositoryImpl ordersRepository;

    public OrdersServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.ordersRepository = new OrdersRepositoryImpl(sqlSessionFactory);
    }

    // Create a new order for a user
//    public void createOrder(int id, int userId, double totalPrice, Date orderDate) throws SQLException {
//        Orders newOrder = new Orders(id, userId, orderDate, totalPrice);
//
//        // Add the new order to the list of orders for the user
//        ordersRepository.addOrder(newOrder);
//        logger.info("Order created successfully for user ID: " + userId);
//    }

    @Override
    public void create(Orders orders) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            OrdersRepository ordersRepository = sqlSession.getMapper(OrdersRepository.class);
            ordersRepository.create(orders);
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            OrdersRepository ordersRepository = sqlSession.getMapper(OrdersRepository.class);
            ordersRepository.deleteById(id);
        }

    }
}
