package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.OrderDetails;
import com.solvd.onlineshop.dao.OrderDetailsRepositoryImpl;
import com.solvd.onlineshop.dao.OrdersRepositoryImpl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDetailsService {

    private static final Logger logger = LogManager.getLogger(OrderDetailsService.class);
    private OrderDetailsRepositoryImpl orderDetailsRepository;
    private OrdersRepositoryImpl ordersRepository;

    public OrderDetailsService() {
        this.orderDetailsRepository = new OrderDetailsRepositoryImpl();
        this.ordersRepository = new OrdersRepositoryImpl();
    }

    // Create a new order detail and check if the order exists
    public void createOrderDetail(int id, int orderId, int productId, int quantity, double subtotal) throws SQLException {
        // Check if the order exists
        if (ordersRepository.getOrderById(orderId) == null) {
            logger.info("Order with ID " + orderId + " does not exist. Cannot create order detail.");
            return;
        }

        // Order exists, create the order detail
        OrderDetails newOrderDetail = new OrderDetails(id, orderId, productId, quantity, subtotal);
        orderDetailsRepository.addOrderDetail(newOrderDetail);
        logger.info("Order detail created successfully: " + newOrderDetail.getId());
    }
}
