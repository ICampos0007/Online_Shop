package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.OrderDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRepositoryImpl {
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(OrderDetailsRepositoryImpl.class);

    // Insert a new order detail
    public void addOrderDetail(OrderDetails orderDetail) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "INSERT INTO orderdetails (id, order_id, product_id, quantity, subtotal) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderDetail.getId());
                preparedStatement.setInt(2, orderDetail.getOrder_Id());
                preparedStatement.setInt(3, orderDetail.getProduct_Id());
                preparedStatement.setInt(4, orderDetail.getQuantity());
                preparedStatement.setDouble(5, orderDetail.getSubtotal());
                preparedStatement.executeUpdate();
                logger.info("Order detail created successfully: " + orderDetail.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating order detail: " + orderDetail.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    // Retrieve an order detail by ID
    public OrderDetails getOrderDetailById(int orderDetailId) {
        OrderDetails orderDetail = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM orderdetails WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderDetailId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        orderDetail = new OrderDetails(
                                resultSet.getInt("id"),
                                resultSet.getInt("order_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("quantity"),
                                resultSet.getDouble("subtotal")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving order detail: " + orderDetailId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return orderDetail;
    }

    // Update order detail information
    public void updateOrderDetail(OrderDetails orderDetail) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "UPDATE orderdetails SET order_id = ?, product_id = ?, quantity = ?, subtotal = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderDetail.getOrder_Id());
                preparedStatement.setInt(2, orderDetail.getProduct_Id());
                preparedStatement.setInt(3, orderDetail.getQuantity());
                preparedStatement.setDouble(4, orderDetail.getSubtotal());
                preparedStatement.setInt(5, orderDetail.getId());
                preparedStatement.executeUpdate();
                logger.info("Order detail updated successfully: " + orderDetail.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating order detail: " + orderDetail.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    // Retrieve an order detail by order ID
    public OrderDetails getOrderDetailByOrderId(int orderId) {
        OrderDetails orderDetail = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM orderdetails WHERE order_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        orderDetail = new OrderDetails(
                                resultSet.getInt("id"),
                                resultSet.getInt("order_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("quantity"),
                                resultSet.getDouble("subtotal")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving order detail by order ID: " + orderId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return orderDetail;
    }
}
