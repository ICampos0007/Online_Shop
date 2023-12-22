package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Products;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(ProductsRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Insert a new product
    public void createProduct(Products product) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO products (id, name, description, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setDouble(4, product.getPrice());
                preparedStatement.executeUpdate();
                logger.info("Product created successfully: " + product.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating product: " + product.getId(), e);
        }
    }

    // Retrieve a product by ID
    public Products getProductById(int productId) {
        Products product = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM products WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        product = new Products(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving product: " + productId, e);
        }
        return product;
    }

    // Update product information
    public void updateProduct(Products product) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE products SET name = ?, description = ?, price = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setInt(4, product.getId());
                preparedStatement.executeUpdate();
                logger.info("Product updated successfully: " + product.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating product: " + product.getId(), e);
        }
    }

    // Retrieve a product by name
    public Products getProductByName(String productName) {
        Products product = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM products WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, productName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        product = new Products(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving product by name: " + productName, e);
        }
        return product;
    }
}