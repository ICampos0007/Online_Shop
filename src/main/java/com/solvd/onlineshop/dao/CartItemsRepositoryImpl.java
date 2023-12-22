package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.CartItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemsRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(CartItemsRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Insert a new cart item
    public void addCartItem(CartItems cartItem) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO cartitems (id, cart_id, product_id, quantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cartItem.getId());
                preparedStatement.setInt(2, cartItem.getCart_Id());
                preparedStatement.setInt(3, cartItem.getProduct_Id());
                preparedStatement.setInt(4, cartItem.getQuantity());
                preparedStatement.executeUpdate();
                logger.info("Cart item created successfully: " + cartItem.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating cart item: " + cartItem.getId(), e);
        }
    }

    // Retrieve a cart item by ID
    public CartItems getCartItemById(int cartItemId) {
        CartItems cartItem = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM cartitems WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cartItemId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cartItem = new CartItems(
                                resultSet.getInt("id"),
                                resultSet.getInt("cart_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("quantity")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving cart item: " + cartItemId, e);
        }
        return cartItem;
    }

    // Update cart item information
    public void updateCartItem(CartItems cartItem) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE cartitems SET cart_id = ?, product_id = ?, quantity = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cartItem.getCart_Id());
                preparedStatement.setInt(2, cartItem.getProduct_Id());
                preparedStatement.setInt(3, cartItem.getQuantity());
                preparedStatement.setInt(4, cartItem.getId());
                preparedStatement.executeUpdate();
                logger.info("Cart item updated successfully: " + cartItem.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating cart item: " + cartItem.getId(), e);
        }
    }

    public CartItems getCartItemByCartId(int cartId) {
        CartItems cartItem = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM cartitems WHERE cart_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cartId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cartItem = new CartItems(
                                resultSet.getInt("id"),
                                resultSet.getInt("cart_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("quantity")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving cart item by cart ID: " + cartId, e);
        }
        return cartItem;
    }
}