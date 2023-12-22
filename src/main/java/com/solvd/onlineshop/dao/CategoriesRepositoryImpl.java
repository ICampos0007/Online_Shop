package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Categories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(CategoriesRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Insert a new category
    public void addCategory(Categories category) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO categories (id, category_name) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, category.getId());
                preparedStatement.setString(2, category.getCategory_Name());
                preparedStatement.executeUpdate();
                logger.info("Category created successfully: " + category.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating category: " + category.getId(), e);
        }
    }

    // Retrieve a category by ID
    public Categories getCategoryById(int categoryId) {
        Categories category = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM categories WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        category = new Categories(
                                resultSet.getInt("id"),
                                resultSet.getString("category_name")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving category: " + categoryId, e);
        }
        return category;
    }

    // Update category information
    public void updateCategory(Categories category) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE categories SET category_name = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, category.getCategory_Name());
                preparedStatement.setInt(2, category.getId());
                preparedStatement.executeUpdate();
                logger.info("Category updated successfully: " + category.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating category: " + category.getId(), e);
        }
    }

    // Retrieve a category by name
    public Categories getCategoryByName(String categoryName) {
        Categories category = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM categories WHERE category_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, categoryName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        category = new Categories(
                                resultSet.getInt("id"),
                                resultSet.getString("category_name")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving category by name: " + categoryName, e);
        }
        return category;
    }

}