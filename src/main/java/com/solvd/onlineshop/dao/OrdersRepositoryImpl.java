package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Orders;
import com.solvd.onlineshop.dao.persistence.OrdersRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OrdersRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(OrdersRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private final SqlSessionFactory sqlSessionFactory;

    public OrdersRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Insert a new order
    public void addOrder(Orders order) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO orders (id, user_id, order_date, total_price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, order.getUser_id());
                preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(order.getOrder_date()));
                preparedStatement.setDouble(4, order.getTotal_price());
                preparedStatement.executeUpdate();
                logger.info("Order created successfully: " + order.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating order: " + order.getId(), e);
        }
    }

    // Retrieve an order by ID
    public Orders getOrderById(int orderId) {
        Orders order = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM orders WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        order = new Orders(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getDate("order_date"),
                                resultSet.getDouble("total_price")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving order: " + orderId, e);
        }
        return order;
    }

    // Update order information
    public void updateOrder(Orders order) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE orders SET user_id = ?, order_date = ?, total_price = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, order.getUser_id());
                preparedStatement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(order.getOrder_date()));
                preparedStatement.setDouble(3, order.getTotal_price());
                preparedStatement.setInt(4, order.getId());
                preparedStatement.executeUpdate();
                logger.info("Order updated successfully: " + order.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating order: " + order.getId(), e);
        }
    }

    // Retrieve an order by User ID
    public Orders getOrderByUserId(int userId) {
        Orders order = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM orders WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        order = new Orders(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getDate("order_date"),
                                resultSet.getDouble("total_price")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving order by user ID: " + userId, e);
        }
        return order;
    }
}
