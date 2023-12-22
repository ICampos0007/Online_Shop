package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Carts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartsRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(CartsRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Insert a new cart
    public void addCart(Carts cart) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO carts (id, user_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cart.getId());
                preparedStatement.setInt(2, cart.getUser_Id());
                preparedStatement.executeUpdate();
                logger.info("Cart created successfully: " + cart.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating cart: " + cart.getId(), e);
        }
    }

    // Retrieve a cart by ID
    public Carts getCartById(int cartId) {
        Carts cart = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM carts WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cartId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cart = new Carts(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving cart: " + cartId, e);
        }
        return cart;
    }

    // Update cart information
    public void updateCart(Carts cart) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE carts SET user_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cart.getUser_Id());
                preparedStatement.setInt(2, cart.getId());
                preparedStatement.executeUpdate();
                logger.info("Cart updated successfully: " + cart.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating cart: " + cart.getId(), e);
        }
    }

    // Retrieve a cart by User ID
    public Carts getCartByUserId(int userId) {
        Carts cart = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM carts WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cart = new Carts(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving cart by user ID: " + userId, e);
        }
        return cart;
    }
}