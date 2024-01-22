package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Carts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CartsRepositoryImpl {
    private static final String CREATE_CART = "INSERT INTO carts (user_id) VALUES (?)";
    private static final String GET_BY_ID = "SELECT * FROM carts WHERE id = ?";
    private static final String UPDATE_CART_QUERY = "UPDATE carts SET user_id = ? WHERE id = ?";
    private static final String GET_BY_USER_ID = "SELECT * FROM carts WHERE user_id = ?";

    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(CartsRepositoryImpl.class);


    // Insert a new cart
    public void addCart(Carts cart) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_CART,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, cart.getUser_Id());
            preparedStatement.executeUpdate();
            logger.info("Cart created successfully: " + cart.getId());
        } catch (SQLException e) {
            logger.error("Error creating cart: " + cart.getId(), e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a cart by ID
    public Carts getCartById(int cartId) {
        Connection connection = ConnectionPool.getConnection();
        Carts cart = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
            preparedStatement.setInt(1, cartId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cart = new Carts(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving cart: " + cartId, e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return cart;
    }

    // Update cart information
    public void updateCart(Carts cart) {
        Connection connection = ConnectionPool.getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_QUERY)
        ) {
            preparedStatement.setInt(1, cart.getUser_Id());
            preparedStatement.setInt(2, cart.getId());
            preparedStatement.executeUpdate();
            logger.info("Cart updated successfully: " + cart.getId());
        } catch (SQLException e) {
            logger.error("Error updating cart: " + cart.getId(), e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a cart by User ID
    public Carts getCartByUserId(int userId) {
        Connection connection = ConnectionPool.getConnection();
        Carts cart = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID)
                ) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cart = new Carts(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id")
                        );
                    }
                }
        } catch (SQLException e) {
            logger.error("Error retrieving cart by user ID: " + userId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return cart;
    }
}