package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Products;
import com.solvd.onlineshop.dao.persistence.ProductsRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProductsRepositoryImpl  {
    private static final String CREATE_PRODUCT = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE products SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT * FROM products WHERE name = ?";
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(ProductsRepositoryImpl.class);
    private final SqlSessionFactory sqlSessionFactory;
    public ProductsRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Insert a new product
    public void createProduct(Products product) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_PRODUCT,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
            logger.info("Product created successfully: " + product.getId());
        } catch (SQLException e) {
            logger.error("Error creating product: " + product.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a product by ID
    public Products getProductById(int productId) {
        Connection connection = ConnectionPool.getConnection();
        Products product = null;
        try (PreparedStatement preparedStatement =connection.prepareStatement(GET_BY_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving product: " + productId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return product;
    }

    // Update product information
    public void updateProduct(Products product) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
            logger.info("Product updated successfully: " + product.getId());
        } catch (SQLException e) {
            logger.error("Error updating product: " + product.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a product by name
    public Products getProductByName(String productName) {
        Connection connection = ConnectionPool.getConnection();
        Products product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving product by name: " + productName, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return product;
    }
}