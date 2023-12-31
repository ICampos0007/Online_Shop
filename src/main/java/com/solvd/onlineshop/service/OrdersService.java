package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Orders;
import com.solvd.onlineshop.dao.OrdersRepositoryImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrdersService {

    private static final Logger logger = LogManager.getLogger(OrdersService.class);
    private final SqlSessionFactory sqlSessionFactory;
    private OrdersRepositoryImpl ordersRepository;

    public OrdersService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.ordersRepository = new OrdersRepositoryImpl(sqlSessionFactory);
    }

    // Create a new order for a user
    public void createOrder(int id, int userId, double totalPrice, Date orderDate) throws SQLException {
        Orders newOrder = new Orders(id, userId, orderDate, totalPrice);

        // Add the new order to the list of orders for the user
        ordersRepository.addOrder(newOrder);
        logger.info("Order created successfully for user ID: " + userId);
    }
}
