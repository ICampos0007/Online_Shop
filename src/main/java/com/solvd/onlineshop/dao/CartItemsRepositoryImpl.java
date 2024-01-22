package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.CartItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CartItemsRepositoryImpl {
    private static final String CREATE_CART_ITEM = "INSERT INTO cartitems (cart_id, product_id, quantity) VALUES (?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM cartitems WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE cartitems SET cart_id = ?, product_id = ?, quantity = ? WHERE id = ?";
    private static final String GET_BY_CART_ID = "SELECT * FROM cartitems WHERE cart_id = ?";

    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(CartItemsRepositoryImpl.class);
    // Insert a new cart item
    public void addCartItem(CartItems cartItem){
        Connection connection = ConnectionPool.getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        CREATE_CART_ITEM,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, cartItem.getCart_Id());
            preparedStatement.setInt(2, cartItem.getProduct_Id());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.executeUpdate();
            logger.info("Cart item created successfully: " + cartItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating cart item: " + cartItem.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a cart item by ID
    public CartItems getCartItemById(int cartItemId) {
        Connection connection = ConnectionPool.getConnection();
        CartItems cartItem = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
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

        } catch (SQLException e) {
            logger.error("Error retrieving cart item: " + cartItemId, e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return cartItem;
    }

    // Update cart item information
    public void updateCartItem(CartItems cartItem) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setInt(1, cartItem.getCart_Id());
            preparedStatement.setInt(2, cartItem.getProduct_Id());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.setInt(4, cartItem.getId());
            preparedStatement.executeUpdate();
            logger.info("Cart item updated successfully: " + cartItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating cart item: " + cartItem.getId(), e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public CartItems getCartItemByCartId(int cartId) {
        Connection connection = ConnectionPool.getConnection();
        CartItems cartItem = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CART_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving cart item by cart ID: " + cartId, e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return cartItem;
    }
}