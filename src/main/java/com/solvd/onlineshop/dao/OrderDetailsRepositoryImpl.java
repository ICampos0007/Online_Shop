package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.OrderDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class OrderDetailsRepositoryImpl {
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO orderdetails (order_id, product_id, quantity, subtotal) " +
            "VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM orderdetails WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE orderdetails SET order_id = ?, product_id = ?, quantity = ?, subtotal = ? WHERE id = ?";
    private static final String GET_BY_ORDER_ID = "SELECT * FROM orderdetails WHERE order_id = ?";
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(OrderDetailsRepositoryImpl.class);

    // Insert a new order detail
    public void addOrderDetail(OrderDetails orderDetail) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_ORDER_DETAIL,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, orderDetail.getOrder_Id());
            preparedStatement.setInt(2, orderDetail.getProduct_Id());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setDouble(4, orderDetail.getSubtotal());
            preparedStatement.executeUpdate();
            logger.info("Order detail created successfully: " + orderDetail.getId());
        } catch (SQLException e) {
            logger.error("Error creating order detail: " + orderDetail.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve an order detail by ID
    public OrderDetails getOrderDetailById(int orderDetailId) {
        Connection connection = ConnectionPool.getConnection();
        OrderDetails orderDetail = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving order detail: " + orderDetailId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return orderDetail;
    }

    // Update order detail information
    public void updateOrderDetail(OrderDetails orderDetail) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setInt(1, orderDetail.getOrder_Id());
            preparedStatement.setInt(2, orderDetail.getProduct_Id());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setDouble(4, orderDetail.getSubtotal());
            preparedStatement.setInt(5, orderDetail.getId());
            preparedStatement.executeUpdate();
            logger.info("Order detail updated successfully: " + orderDetail.getId());
        } catch (SQLException e) {
            logger.error("Error updating order detail: " + orderDetail.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve an order detail by order ID
    public OrderDetails getOrderDetailByOrderId(int orderId) {
        Connection connection = ConnectionPool.getConnection();
        OrderDetails orderDetail = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ORDER_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving order detail by order ID: " + orderId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return orderDetail;
    }
}
