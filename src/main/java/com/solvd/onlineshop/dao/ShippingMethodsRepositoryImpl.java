package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.ShippingMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShippingMethodsRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(ShippingMethodsRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Insert a new shipping method
    public void addShippingMethod(ShippingMethods shippingMethod) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO shippingmethods (id, shipping_method_name, shipping_cost, order_id) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, shippingMethod.getId());
                preparedStatement.setString(2, shippingMethod.getShipping_Method_Name());
                preparedStatement.setDouble(3, shippingMethod.getShipping_Cost());
                preparedStatement.setInt(4, shippingMethod.getOrder_Id());
                preparedStatement.executeUpdate();
                logger.info("Shipping method created successfully: " + shippingMethod.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating shipping method: " + shippingMethod.getId(), e);
        }
    }

    // Retrieve a shipping method by ID
    public ShippingMethods getShippingMethodById(int shippingMethodId) {
        ShippingMethods shippingMethod = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM shippingmethods WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, shippingMethodId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        shippingMethod = new ShippingMethods(
                                resultSet.getInt("id"),
                                resultSet.getString("shipping_method_name"),
                                resultSet.getDouble("shipping_cost"),
                                resultSet.getInt("order_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving shipping method: " + shippingMethodId, e);
        }
        return shippingMethod;
    }

    // Update shipping method information
    public void updateShippingMethod(ShippingMethods shippingMethod) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE shippingmethods SET shipping_method_name = ?, shipping_cost = ?, order_id = ? " +
                    "WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, shippingMethod.getShipping_Method_Name());
                preparedStatement.setDouble(2, shippingMethod.getShipping_Cost());
                preparedStatement.setInt(3, shippingMethod.getOrder_Id());
                preparedStatement.setInt(4, shippingMethod.getId());
                preparedStatement.executeUpdate();
                logger.info("Shipping method updated successfully: " + shippingMethod.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating shipping method: " + shippingMethod.getId(), e);
        }
    }

    // Retrieve a shipping method by order ID
    public ShippingMethods getShippingMethodByOrderId(int orderId) {
        ShippingMethods shippingMethod = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM shippingmethods WHERE order_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        shippingMethod = new ShippingMethods(
                                resultSet.getInt("id"),
                                resultSet.getString("shipping_method_name"),
                                resultSet.getDouble("shipping_cost"),
                                resultSet.getInt("order_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving shipping method by order ID: " + orderId, e);
        }
        return shippingMethod;
    }
}
