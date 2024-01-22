package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Orders;
import com.solvd.onlineshop.dao.persistence.OrdersRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import java.text.SimpleDateFormat;

public class OrdersRepositoryImpl {
    private static final String CREATE_ORDER = "INSERT INTO orders (user_id, order_date, total_price) VALUES (?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE orders SET user_id = ?, order_date = ?, total_price = ? WHERE id = ?";
    private static final String GET_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(OrdersRepositoryImpl.class);


    private final SqlSessionFactory sqlSessionFactory;

    public OrdersRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Insert a new order
    public void addOrder(Orders order) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_ORDER,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(order.getOrder_date()));
            preparedStatement.setDouble(3, order.getTotal_price());
            preparedStatement.executeUpdate();
            logger.info("Order created successfully: " + order.getId());
        } catch (SQLException e) {
            logger.error("Error creating order: " + order.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve an order by ID
    public Orders getOrderById(int orderId) {
        Connection connection = ConnectionPool.getConnection();
        Orders order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving order: " + orderId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return order;
    }

    // Update order information
    public void updateOrder(Orders order) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(order.getOrder_date()));
            preparedStatement.setDouble(3, order.getTotal_price());
            preparedStatement.setInt(4, order.getId());
            preparedStatement.executeUpdate();
            logger.info("Order updated successfully: " + order.getId());
        } catch (SQLException e) {
            logger.error("Error updating order: " + order.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve an order by User ID
    public Orders getOrderByUserId(int userId) {
        Connection connection = ConnectionPool.getConnection();
        Orders order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving order by user ID: " + userId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return order;
    }
}
